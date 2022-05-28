package com.example.dogadoptapp.di

import android.content.Context
import com.example.dogadoptapp.data.repository.DataStoreOperationsImpl
import com.example.dogadoptapp.data.repository.Repository
import com.example.dogadoptapp.domain.repository.DataStoreOperations
import com.example.dogadoptapp.domain.use_cases.UseCases
import com.example.dogadoptapp.domain.use_cases.get_all_dogs.GetAllDogsUseCase
import com.example.dogadoptapp.domain.use_cases.get_selected_dog.GetSelectedDogUseCase
import com.example.dogadoptapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.dogadoptapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.dogadoptapp.domain.use_cases.search_dogs.SearchDogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllDogsUseCase = GetAllDogsUseCase(repository),
            searchDogUseCase = SearchDogUseCase(repository),
            getSelectedDogUseCase = GetSelectedDogUseCase(repository)
        )
    }

}