package com.roxx.grigarage.presentation.screens.tracker.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxx.grigarage.domain.model.UserProfile
import com.roxx.grigarage.domain.use_cases.another.SetWeeklyGoalUseCase
import com.roxx.grigarage.domain.use_cases.another.UserProfileUseCase
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    private val setWeeklyGoalUseCase: SetWeeklyGoalUseCase
) : ViewModel() {
    private val _user = mutableStateOf<UserProfile?>(null)
    val user: State<UserProfile?> = _user

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var beerCount = mutableStateOf(0)
        private set

    init {
        loadLatestUser()
    }

    fun onClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.FAVORITE))
        }
    }

    fun updateBeerCount(newCount: Int) {
        beerCount.value = if (newCount >= 0) newCount else 0
    }

    fun updatedBeer() {
        val goal = if (beerCount.value < 0) 10 else beerCount.value
        setWeeklyGoalUseCase(goal)
        loadLatestUser()
    }

    private fun loadLatestUser() {
        viewModelScope.launch {
            _user.value = userProfileUseCase()
        }
    }
}