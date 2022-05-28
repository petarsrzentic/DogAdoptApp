package com.example.dogadoptapp.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val dogs: List<Dog> = emptyList(),
    val lastUpdated: Long? = null
)
