package com.example.dogadoptapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogadoptapp.domain.model.Dog

@Dao
interface DogDao {

    @Query("SELECT * FROM dog_table ORDER BY id ASC")
    fun getAllDogs(): PagingSource<Int, Dog>

    @Query("SELECT * FROM dog_table WHERE id=:dogId")
    fun getSelectedDog(dogId: Int): Dog

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDogs(dogs: List<Dog>)

    @Query("DELETE FROM dog_table")
    suspend fun deleteAllDogs()
}