package com.example.dogadoptapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dogadoptapp.data.remote.DogApi
import com.example.dogadoptapp.domain.model.Dog
import javax.inject.Inject

class SearchDogSource @Inject constructor(
    private val dogApi: DogApi,
    private val query: String
): PagingSource<Int, Dog>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dog> {
        return try {
           val apiResponse = dogApi.searchDogs(query)
            val dogs = apiResponse.dogs
            if (dogs.isNotEmpty()) {
                LoadResult.Page(
                    data = dogs,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Dog>): Int? {
        return state.anchorPosition
    }
}