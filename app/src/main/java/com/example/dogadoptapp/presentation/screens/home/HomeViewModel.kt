package com.example.dogadoptapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.example.dogadoptapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllDogs = useCases.getAllDogsUseCase()
}