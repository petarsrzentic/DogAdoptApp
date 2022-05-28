package com.example.dogadoptapp.domain.use_cases

import com.example.dogadoptapp.domain.use_cases.get_all_dogs.GetAllDogsUseCase
import com.example.dogadoptapp.domain.use_cases.get_selected_dog.GetSelectedDogUseCase
import com.example.dogadoptapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.dogadoptapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.dogadoptapp.domain.use_cases.search_dogs.SearchDogUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllDogsUseCase: GetAllDogsUseCase,
    val searchDogUseCase: SearchDogUseCase,
    val getSelectedDogUseCase: GetSelectedDogUseCase
)