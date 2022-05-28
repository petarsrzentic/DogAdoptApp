package com.example.dogadoptapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dogadoptapp.data.local.DogDatabase
import com.example.dogadoptapp.data.paging_source.DogRemoteMediator
import com.example.dogadoptapp.data.paging_source.SearchDogSource
import com.example.dogadoptapp.data.remote.DogApi
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.domain.repository.RemoteDataSource
import com.example.dogadoptapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val dogApi: DogApi,
    private val dogDatabase: DogDatabase
): RemoteDataSource {

    private val dogDao = dogDatabase.dogDao()

    override fun getAllDogs(): Flow<PagingData<Dog>> {
        val pagingSourceFactory = { dogDao.getAllDogs() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = DogRemoteMediator(
                dogApi = dogApi,
                dogDatabase = dogDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchDogs(query: String): Flow<PagingData<Dog>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchDogSource(dogApi = dogApi, query = query)
            }
        ).flow
    }
}