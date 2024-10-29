package com.roxx.grigarage.domain.use_cases.another

import com.roxx.grigarage.domain.preferences.Preferences

class SetOnboardingStateUseCase(private val preferences: Preferences) {

    operator fun invoke(state: Boolean) {
        preferences.saveShouldShowOnboarding(state)
    }
}