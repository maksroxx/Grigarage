package com.roxx.grigarage.presentation.screens.tracker.search

import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel

sealed class SearchEvent {
    data class OnQueryChange(val query: String): SearchEvent()
    object OnSearch: SearchEvent()
    data class OnBeerClick(val id: Int): SearchEvent()
    data class OnBeerLiked(val beer: BeerUiModel): SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent()
}