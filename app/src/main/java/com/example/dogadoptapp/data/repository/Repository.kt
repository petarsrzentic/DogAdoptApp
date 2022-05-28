package com.example.dogadoptapp.data.repository

import androidx.paging.PagingData
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.domain.repository.DataStoreOperations
import com.example.dogadoptapp.domain.repository.LocalDataSource
import com.example.dogadoptapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
){

    fun getAllDogs(): Flow<PagingData<Dog>> {
        return remote.getAllDogs()
    }

    fun searchDogs(query: String): Flow<PagingData<Dog>> {
        return remote.searchDogs(query = query)
    }

    suspend fun getSelectedDog(dogId: Int): Dog {
        return local.getSelectedDog(dogId = dogId)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}