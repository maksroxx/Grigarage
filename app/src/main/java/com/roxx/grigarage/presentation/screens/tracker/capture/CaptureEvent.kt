package com.roxx.grigarage.presentation.screens.tracker.capture

sealed class CaptureEvent {
    data class OnNameChange(val newName: String): CaptureEvent()
    data class OnBrandChange(val newBrand: String): CaptureEvent()
    data class OnTypeChange(val newType: String): CaptureEvent()
    data class OnAlcoholChange(val newPercentage: Float): CaptureEvent()
    data class OnVolumeChange(val newVolume: Float): CaptureEvent()
    data class OnNotesChange(val newNotes: String): CaptureEvent()
    object TakePhoto: CaptureEvent()
    object OnClick: CaptureEvent()
    object OnDismiss: CaptureEvent()
}