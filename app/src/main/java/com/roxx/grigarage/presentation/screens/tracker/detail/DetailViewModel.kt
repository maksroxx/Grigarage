package com.roxx.grigarage.presentation.screens.tracker.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.use_cases.BeersUseCases
import com.roxx.grigarage.domain.use_cases.another.ConvertBase64ToImageBitmapUseCase
import com.roxx.grigarage.domain.use_cases.another.ConvertBitmapToBase64UseCase
import com.roxx.grigarage.domain.use_cases.beers.UpdateBeerUseCase
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.toBeer
import com.roxx.grigarage.presentation.util.toBeerUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val updateBeerUseCase: UpdateBeerUseCase,
    private val baseToBitmap: ConvertBase64ToImageBitmapUseCase,
    private val imageToString: ConvertBitmapToBase64UseCase,
    private val beersUseCases: BeersUseCases
) : ViewModel() {

    private val _beer = mutableStateOf<BeerUiModel?>(null)
    val beer: State<BeerUiModel?> = _beer

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
                    _uiEvent.send(UiEvent.Navigate(Route.MAIN))
                }
            }
            is DetailEvent.Increment -> {
                viewModelScope.launch {
                    beersUseCases.incrementDrinkCountUseCase(event.id)
                }
            }
            is DetailEvent.LikeBeer -> {
                viewModelScope.launch {
                    _beer.value?.let {
                        updateBeerUseCase(
                            it.toBeer(imageToString(_beer.value!!.photoUri.asAndroidBitmap()))
                        )
                    }
                }
            }
            is DetailEvent.LoadBeer -> {
                viewModelScope.launch {
                    _beer.value = beersUseCases.getBeerByIdUseCase(event.id).let { beer ->
                        beer?.toBeerUiModel(baseToBitmap(beer.photoUri))
                    }
                }
            }
        }
    }
}