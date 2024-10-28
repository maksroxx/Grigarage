package com.roxx.grigarage.presentation.screens.tracker.favorite

import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel

sealed class FavoriteEvent {
    object OnButtonClick: FavoriteEvent()
    object OnArrowClick: FavoriteEvent()
    data class OnBeerClick(val id: Int): FavoriteEvent()
    data class OnBeerLiked(val beer: BeerUiModel): FavoriteEvent()
}