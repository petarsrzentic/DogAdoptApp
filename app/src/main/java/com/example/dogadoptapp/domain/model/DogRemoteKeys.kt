package com.example.dogadoptapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dogadoptapp.util.Constants.DOG_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = DOG_REMOTE_KEYS_DATABASE_TABLE)
data class DogRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)