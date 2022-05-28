package com.example.dogadoptapp.domain.use_cases.get_selected_dog

import com.example.dogadoptapp.data.repository.Repository
import com.example.dogadoptapp.domain.model.Dog

class GetSelectedDogUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(dogId: Int): Dog{
        return repository.getSelectedDog(dogId = dogId)
    }
}