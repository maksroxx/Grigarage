package com.roxx.grigarage.domain.use_cases.another

import com.roxx.grigarage.domain.preferences.Preferences

class LoadOnboardingStateUseCase(private val preferences: Preferences) {

    operator fun invoke(): Boolean {
        return preferences.loadShouldShowOnboarding()
    }
}