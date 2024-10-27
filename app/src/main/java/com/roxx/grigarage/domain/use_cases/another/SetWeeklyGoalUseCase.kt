package com.roxx.grigarage.domain.use_cases.another

import com.roxx.grigarage.domain.preferences.Preferences

class SetWeeklyGoalUseCase(private val preferences: Preferences) {

    operator fun invoke(count: Int) {
        preferences.setWeeklyGoal(count)
    }
}