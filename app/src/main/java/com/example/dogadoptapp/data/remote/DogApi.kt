package com.example.dogadoptapp.data.remote

import com.example.dogadoptapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {

    @GET("/dog/allDogs")
    suspend fun getAllDogs(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/dog/allDogs/search")
    suspend fun searchDogs(
        @Query("name") name: String
    ): ApiResponse
}