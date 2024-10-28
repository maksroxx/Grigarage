package com.roxx.grigarage.presentation.screens.tracker.main

import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel

sealed class MainEvent {
    object OnButtonClick: MainEvent()
    data class OnBeerClick(val id: Int): MainEvent()
    data class OnBeerLiked(val beer: BeerUiModel): MainEvent()
}