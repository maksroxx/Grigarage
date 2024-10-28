package com.roxx.grigarage.presentation.screens.tracker.profile

sealed class ProfileEvent {
    object OnClick: ProfileEvent()
    object UpdatedBeer: ProfileEvent()
    data class UpdateBeerCount(val newCount: Int): ProfileEvent()
}