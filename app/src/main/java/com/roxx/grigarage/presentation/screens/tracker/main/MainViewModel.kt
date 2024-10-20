package com.roxx.grigarage.presentation.screens.tracker.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.use_cases.BeersUseCases
import com.roxx.grigarage.domain.use_cases.another.ConvertBase64ToImageBitmapUseCase
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel
import com.roxx.grigarage.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    preferences: Preferences,
    beersUseCase: BeersUseCases,
    base64ToImageBitmapUseCase: ConvertBase64ToImageBitmapUseCase
) : ViewModel() {
    val beers = beersUseCase
        .getAllBeersUseCase.invoke()
        .map { pagingData ->
            pagingData.map { beer ->
                BeerUiModel(
                    name = beer.name,
                    brand = beer.brand,
                    type = beer.type,
                    alcoholPercentage = beer.alcoholPercentage,
                    volume = beer.volume,
                    color = beer.color,
                    rating = beer.rating,
                    notes = beer.notes,
                    photoUri = base64ToImageBitmapUseCase(beer.photoUri),
                    dateAdded = beer.dateAdded,
                    isFavorite = beer.isFavorite,
                    isWishlist = beer.isWishlist,
                    drinkCount = beer.drinkCount
                )
            }
        }
        .cachedIn(viewModelScope)

    init {
        preferences.saveShouldShowOnboarding(false)
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onBeerClicked() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.DETAIL))
        }
    }
}