package com.roxx.grigarage.presentation.screens.onboarding.welcome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    val preferences: Preferences
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        if (!preferences.loadShouldShowOnboarding()) {
            viewModelScope.launch {
                _uiEvent.send(UiEvent.Navigate(Route.MAIN))
            }
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.INFO))
        }
    }
}