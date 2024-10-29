package com.roxx.grigarage.presentation.screens.tracker.detail

import android.util.Log
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.use_cases.BeersUseCases
import com.roxx.grigarage.domain.use_cases.another.ConvertBase64ToImageBitmapUseCase
import com.roxx.grigarage.domain.use_cases.another.ConvertBitmapToBase64UseCase
import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.toBeer
import com.roxx.grigarage.presentation.util.toBeerUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val baseToBitmap: ConvertBase64ToImageBitmapUseCase,
    private val imageToString: ConvertBitmapToBase64UseCase,
    private val beersUseCases: BeersUseCases
) : ViewModel() {

    private val _beer = MutableStateFlow<BeerUiModel?>(null)
    val beer: StateFlow<BeerUiModel?> = _beer.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.Decrement -> {
                viewModelScope.launch {
                    beersUseCases.decrementDrinkCountUseCase(event.id)
                }
            }

            is DetailEvent.DeleteBeer -> {
                viewModelScope.launch {
                    _beer.value?.let {
                        beersUseCases.deleteBeerUseCase(
                            it.toBeer(imageToString(_beer.value!!.photoUri.asAndroidBitmap()))
                        )
                    }
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }

            is DetailEvent.Increment -> {
                viewModelScope.launch {
                    beersUseCases.incrementDrinkCountUseCase(event.id)
                }
            }

            is DetailEvent.LoadBeer -> {
                viewModelScope.launch {
                    beersUseCases.getBeerByIdUseCase(event.id).collect { beer ->
                        Log.d("Detail", "Load beer: ${beer?.id}")
                        _beer.value = beer?.toBeerUiModel(baseToBitmap(beer.photoUri))
                    }
                }
            }

            is DetailEvent.GoBack -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }
        }
    }
}