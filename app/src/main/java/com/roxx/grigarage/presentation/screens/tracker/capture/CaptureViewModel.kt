package com.roxx.grigarage.presentation.screens.tracker.capture

import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.use_cases.another.TakePhotoUseCase
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaptureViewModel @Inject constructor(
    private val takePhotoUseCase: TakePhotoUseCase,
    cameraController: LifecycleCameraController
) : ViewModel() {
    val cameraControllerFlow = MutableStateFlow(cameraController)

    private val _bitmap = MutableStateFlow<Bitmap?>(null)
    val bitmap: StateFlow<Bitmap?> = _bitmap

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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

    fun onNextClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.ADD))
        }
    }
}