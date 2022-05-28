package com.example.dogadoptapp.data.repository

import com.example.dogadoptapp.data.local.DogDatabase
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(dogDatabase: DogDatabase): LocalDataSource {

    private val dogDao = dogDatabase.dogDao()

    override suspend fun getSelectedDog(dogId: Int): Dog {
        return dogDao.getSelectedDog(dogId)
    }
}