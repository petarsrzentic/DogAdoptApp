package com.example.dogadoptapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogadoptapp.data.local.dao.DogDao
import com.example.dogadoptapp.data.local.dao.DogRemoteKeysDao
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.domain.model.DogRemoteKeys

@Database(entities = [Dog::class, DogRemoteKeys::class], version = 1)
abstract class DogDatabase: RoomDatabase() {

    abstract fun dogDao(): DogDao
    abstract fun dogRemoteKeysDao(): DogRemoteKeysDao

}