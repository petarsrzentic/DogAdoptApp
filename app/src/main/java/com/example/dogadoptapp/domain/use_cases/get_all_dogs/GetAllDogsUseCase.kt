package com.example.dogadoptapp.domain.use_cases.get_all_dogs

import androidx.paging.PagingData
import com.example.dogadoptapp.data.repository.Repository
import com.example.dogadoptapp.domain.model.Dog
import kotlinx.coroutines.flow.Flow

class GetAllDogsUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<Dog>> {
        return repository.getAllDogs()
    }

}