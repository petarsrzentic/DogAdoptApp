package com.example.dogadoptapp.domain.repository

import com.example.dogadoptapp.domain.model.Dog

interface LocalDataSource {
    suspend fun getSelectedDog(dogId: Int): Dog
}