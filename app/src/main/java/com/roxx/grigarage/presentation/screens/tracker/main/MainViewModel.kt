package com.roxx.grigarage.presentation.screens.tracker.main

import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.roxx.grigarage.domain.use_cases.another.ConvertBase64ToImageBitmapUseCase
import com.roxx.grigarage.domain.use_cases.another.ConvertBitmapToBase64UseCase
import com.roxx.grigarage.domain.use_cases.another.SetOnboardingStateUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetAllBeersUseCase
import com.roxx.grigarage.domain.use_cases.beers.UpdateBeerUseCase
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.toBeer
import com.roxx.grigarage.presentation.util.toBeerUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    setOnboardingStateUseCase: SetOnboardingStateUseCase,
    private val getAllBeersUseCase: GetAllBeersUseCase,
    private val updateBeerUseCase: UpdateBeerUseCase,
    private val baseToBitmap: ConvertBase64ToImageBitmapUseCase,
    private val imageToString: ConvertBitmapToBase64UseCase
) : ViewModel() {
    private val _beers = MutableStateFlow<PagingData<BeerUiModel>>(PagingData.empty())
    val beers: StateFlow<PagingData<BeerUiModel>> = _beers

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        setOnboardingStateUseCase(false)

        viewModelScope.launch {
            getAllBeersUseCase.invoke()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _beers.value = pagingData.map { beer ->
                        beer.toBeerUiModel(baseToBitmap(beer.photoUri))
                    }
                }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnBeerClick -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(Route.DETAIL + "/${event.id}"))
                }
            }

            is MainEvent.OnBeerLiked -> {
                viewModelScope.launch {
                    updateBeerUseCase(event.beer.toBeer(imageToString(event.beer.photoUri.asAndroidBitmap())))
                }
            }

            is MainEvent.OnButtonClick -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(Route.CAPTURE))
                }
            }
        }
    }
}