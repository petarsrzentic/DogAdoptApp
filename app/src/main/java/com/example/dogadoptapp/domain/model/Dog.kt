package com.example.dogadoptapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dogadoptapp.util.Constants.DOG_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = DOG_DATABASE_TABLE)
data class Dog(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val gender: String,
    val distance: String,
    val age: Double,
    val weight: Double,
    val color: String
)
