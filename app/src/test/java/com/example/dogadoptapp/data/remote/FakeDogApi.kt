package com.example.dogadoptapp.data.remote

import com.example.dogadoptapp.domain.model.ApiResponse
import com.example.dogadoptapp.domain.model.Dog

class FakeDogApi: DogApi {

    private val dogs = listOf(
        Dog(
            id = 1,
            name = "Davis",
            image = "/images/Davis.jpg",
            about = "Davis is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
            gender = "male",
            distance = "224m away",
            age = 1.5,
            weight = 14.1,
            color = "creme"
        ),
        Dog(
            id = 2,
            name = "Marry",
            image = "/images/Marry.jpg",
            about = "Marry is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
            gender = "female",
            distance = "224m away",
            age = 1.0,
            weight = 6.8,
            color = "black"
        ),
        Dog(
            id = 3,
            name = "Alicia",
            image = "/images/Alicia.jpg",
            about = "Alicia is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
            gender = "female",
            distance = "748m away",
            age = 3.5,
            weight = 7.7,
            color = "black/white/brown"
        )
    )

    override suspend fun getAllDogs(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun searchDogs(name: String): ApiResponse {
        val searchedDog = findDogs(name = name)
        return ApiResponse(
            success = true,
            message = "ok",
            dogs = searchedDog
        )
    }

    private fun findDogs(name: String): List<Dog> {
        val founded = mutableListOf<Dog>()
        return if (name.isNotEmpty()) {
            dogs.forEach { dog ->
                if (dog.name.lowercase().contains(name.lowercase())) {
                    founded.add(dog)
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}