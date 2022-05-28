package com.example.dogadoptapp.domain.use_cases.search_dogs

import androidx.paging.PagingData
import com.example.dogadoptapp.data.repository.Repository
import com.example.dogadoptapp.domain.model.Dog
import kotlinx.coroutines.flow.Flow

class SearchDogUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Dog>> {
        return repository.searchDogs(query = query)
    }
}