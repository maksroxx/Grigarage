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

    var notes by mutableStateOf("")
        private set

    fun onEvent(event: CaptureEvent) {
        when (event) {
            is CaptureEvent.OnAlcoholChange -> {
                alcoholPercentage = event.newPercentage
            }

            is CaptureEvent.OnBrandChange -> {
                brand = event.newBrand
            }

            is CaptureEvent.OnClick -> {
                viewModelScope.launch {
                    bitmap.value?.let { bitmapToBase64UseCase(it) }?.let {
                        Beer(
                            name = name,
                            brand = brand,
                            type = type,
                            alcoholPercentage = alcoholPercentage,
                            volume = volume,
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

            is CaptureEvent.OnDismiss -> {
                _bitmap.value = null
            }

            is CaptureEvent.OnNameChange -> {
                name = event.newName
            }

            is CaptureEvent.OnNotesChange -> {
                notes = event.newNotes
            }

            is CaptureEvent.OnTypeChange -> {
                type = event.newType
            }

            is CaptureEvent.OnVolumeChange -> {
                volume = event.newVolume
            }

            is CaptureEvent.TakePhoto -> {
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
        }
    }
}