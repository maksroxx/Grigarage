package com.roxx.grigarage.presentation.screens.onboarding.name

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.use_cases.another.FilterOutLetter
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameScreenViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutLetter: FilterOutLetter
): ViewModel() {
    var name by mutableStateOf("Bob")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNameEnter(name: String) {
        this.name = filterOutLetter(name)
    }

    fun onNextClick() {
        viewModelScope.launch {
            if (name.isEmpty()) {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.DynamicString("Name cannot be empty")
                    )
                )
                return@launch
            }
            preferences.setUserName(name)
            _uiEvent.send(UiEvent.Navigate(Route.GOAL))
        }
    }
}