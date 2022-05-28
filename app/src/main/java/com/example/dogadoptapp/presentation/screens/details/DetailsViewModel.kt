package com.example.dogadoptapp.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.domain.use_cases.UseCases
import com.example.dogadoptapp.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    /* to fetch dogId */
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedDog: MutableStateFlow<Dog?> = MutableStateFlow(null)
    val selectedDog: StateFlow<Dog?> = _selectedDog

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dogId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedDog.value = dogId?.let {
                useCases.getSelectedDogUseCase(dogId = dogId)
            }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun generateColoPalette() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }

}

sealed class UiEvent {

    object GenerateColorPalette : UiEvent()

}