package com.roxx.grigarage.presentation.screens.onboarding.logo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.use_cases.another.LoadOnboardingStateUseCase
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoViewModel @Inject constructor(
    loadOnboardingStateUseCase: LoadOnboardingStateUseCase
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        if (!loadOnboardingStateUseCase()) {
            viewModelScope.launch {
                _uiEvent.send(UiEvent.Navigate(Route.MAIN))
            }
        } else {
            viewModelScope.launch {
                _uiEvent.send(UiEvent.Navigate(Route.WELCOME))
            }
        }
    }
}