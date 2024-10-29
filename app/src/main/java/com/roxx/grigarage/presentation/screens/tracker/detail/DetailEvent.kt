package com.roxx.grigarage.presentation.screens.tracker.detail

sealed class DetailEvent {
    data class LoadBeer(val id: Int): DetailEvent()
    data class Increment(val id: Int): DetailEvent()
    data class Decrement(val id: Int): DetailEvent()
    object DeleteBeer: DetailEvent()
    object GoBack: DetailEvent()
}