package com.roxx.grigarage.presentation.screens.tracker.capture

import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.use_cases.another.ConvertBitmapToBase64UseCase
import com.roxx.grigarage.domain.use_cases.another.TakePhotoUseCase
import com.roxx.grigarage.domain.use_cases.beers.InsertBeerUseCase
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CaptureViewModel @Inject constructor(
    private val takePhotoUseCase: TakePhotoUseCase,
    cameraController: LifecycleCameraController,
    private val bitmapToBase64UseCase: ConvertBitmapToBase64UseCase,
    private val insertBeerUseCase: InsertBeerUseCase
) : ViewModel() {
    val cameraControllerFlow = MutableStateFlow(cameraController)

    private val _bitmap = MutableStateFlow<Bitmap?>(null)
    val bitmap: StateFlow<Bitmap?> = _bitmap

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var name by mutableStateOf("")
        private set

    var brand by mutableStateOf("")
        private set

    var type by mutableStateOf("")
        private set

    var alcoholPercentage by mutableStateOf(0f)
        private set

    var volume by mutableStateOf(0f)
        private set

    var color by mutableStateOf("light")
        private set

    var notes by mutableStateOf("")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onBrandChange(newBrand: String) {
        brand = newBrand
    }

    fun onTypeChange(newType: String) {
        type = newType
    }

    fun onAlcoholChange(newPercentage: Float) {
        alcoholPercentage = newPercentage
    }

    fun onVolumeChange(newVolume: Float) {
        volume = newVolume
    }

    fun onColorChange(newColor: String) {
        color = newColor
    }

    fun onNotesChange(newNotes: String) {
        notes = newNotes
    }

    fun takePhoto() {
        takePhotoUseCase(
            onPhotoTaken = { photo ->
                _bitmap.value = photo
            },
            onError = {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.ShowSnackbar(UiText.DynamicString("Something went wrong")))
                    return@launch
                }
            }
        )
    }

    fun onClick() {
        viewModelScope.launch {
            bitmap.value?.let { bitmapToBase64UseCase(it) }?.let {
                Beer(
                    name = name,
                    brand = brand,
                    type = type,
                    alcoholPercentage = alcoholPercentage,
                    volume = volume,
                    color = color,
                    notes = notes,
                    photoUri = it,
                    dateAdded = Date.from(Instant.now()).time
                )
            }?.let {
                insertBeerUseCase(
                    it
                )
            }
            _uiEvent.send(UiEvent.Navigate(Route.MAIN))
        }
    }

    fun onDismiss() {
        _bitmap.value = null
    }
}