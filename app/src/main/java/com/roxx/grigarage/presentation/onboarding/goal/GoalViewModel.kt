package com.roxx.grigarage.presentation.onboarding.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.use_cases.another.FilterOutDigit
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigit: FilterOutDigit
) : ViewModel() {
    var goal by mutableStateOf("10")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalEnter(goal: String) {
        this.goal = filterOutDigit(goal)
    }

    fun onNextClick() {
        viewModelScope.launch {
            val goalNumber = goal.toIntOrNull() ?: run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.DynamicString("Goal cant be empty")
                    )
                )
                return@launch
            }
            preferences.setWeeklyGoal(goalNumber)
            _uiEvent.send(UiEvent.Navigate(Route.MAIN))
        }
    }
}