package com.example.dogadoptapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.dogadoptapp.data.local.DogDatabase
import com.example.dogadoptapp.data.remote.DogApi
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.domain.model.DogRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class DogRemoteMediator @Inject constructor(
    private val dogApi: DogApi,
    private val dogDatabase: DogDatabase
) : RemoteMediator<Int, Dog>() {

    private val dogDao = dogDatabase.dogDao()
    private val dogRemoteKeysDao = dogDatabase.dogRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = dogRemoteKeysDao.getRemoteKeys(1)?.lastUpdated ?: 0L
        val cashTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60

        return if (diffInMinutes.toInt() <= cashTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Dog>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }
            // proveriti ovo ako kod ne radi 47-49
            val response = dogApi.getAllDogs(page = page)
            if (response.dogs.isNotEmpty()) {
                //withTransaction will allow to execute multiple database operations, sequentially one by one
                dogDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        dogDao.deleteAllDogs()
                        dogRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.dogs.map { dog ->
                        DogRemoteKeys(
                            id = dog.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    dogRemoteKeysDao.addAllRemoteKeys(dogRemoteKeys = keys)
                    dogDao.addDogs(dogs = response.dogs)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, Dog>
    ): DogRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                dogRemoteKeysDao.getRemoteKeys(dogId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Dog>
    ): DogRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { dog ->
            dogRemoteKeysDao.getRemoteKeys(dogId = dog.id)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, Dog>
    ): DogRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { dog ->
            dogRemoteKeysDao.getRemoteKeys(dogId = dog.id)
        }
    }
}