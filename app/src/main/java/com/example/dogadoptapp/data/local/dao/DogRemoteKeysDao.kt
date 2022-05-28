package com.example.dogadoptapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogadoptapp.domain.model.DogRemoteKeys

@Dao
interface DogRemoteKeysDao {

    @Query("SELECT * FROM dog_remote_keys_table WHERE id=:dogId")
    suspend fun getRemoteKeys(dogId: Int): DogRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(dogRemoteKeys: List<DogRemoteKeys>)

    @Query("DELETE FROM dog_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}