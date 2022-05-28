package com.example.dogadoptapp.domain.repository

import androidx.paging.PagingData
import com.example.dogadoptapp.domain.model.Dog
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface RemoteDataSource {

    fun getAllDogs(): Flow<PagingData<Dog>>
    fun searchDogs(query: String): Flow<PagingData<Dog>>

}