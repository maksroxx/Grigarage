package com.roxx.grigarage

import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.use_cases.another.LoadOnboardingStateUseCase
import com.roxx.grigarage.domain.use_cases.another.SetOnboardingStateUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class OnboardingUseCaseTest {

    private val preferences: Preferences = mockk(relaxed = true)
    private val setOnboardingStateUseCase = SetOnboardingStateUseCase(preferences)
    private val loadOnboardingStateUseCase = LoadOnboardingStateUseCase(preferences)

    @Test
    fun `should save onboarding state as true`() {
        setOnboardingStateUseCase(true)
        verify { preferences.saveShouldShowOnboarding(true) }
    }

    @Test
    fun `should save onboarding state as false`() {
        setOnboardingStateUseCase(false)
        verify { preferences.saveShouldShowOnboarding(false) }
    }

    @Test
    fun `should return true when onboarding should be shown`() {
        every { preferences.loadShouldShowOnboarding() } returns true
        val result = loadOnboardingStateUseCase()
        assertTrue(result)
    }

    @Test
    fun `should return false when onboarding should not be shown`() {
        every { preferences.loadShouldShowOnboarding() } returns false
        val result = loadOnboardingStateUseCase()
        assertFalse(result)
    }
}