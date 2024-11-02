package com.roxx.grigarage.di

import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository
import com.roxx.grigarage.domain.use_cases.another.LoadOnboardingStateUseCase
import com.roxx.grigarage.domain.use_cases.another.SetOnboardingStateUseCase
import com.roxx.grigarage.domain.use_cases.another.SetWeeklyGoalUseCase
import com.roxx.grigarage.domain.use_cases.another.UserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideUserProfileUseCase(
        preferences: Preferences,
        repository: BeerRepository
    ): UserProfileUseCase {
        return UserProfileUseCase(preferences, repository)
    }

    @Provides
    @Singleton
    fun provideOnboardingState(preferences: Preferences): LoadOnboardingStateUseCase {
        return LoadOnboardingStateUseCase(preferences)
    }

    @Provides
    @Singleton
    fun provideSetOnboardingState(preferences: Preferences): SetOnboardingStateUseCase {
        return SetOnboardingStateUseCase(preferences)
    }

    @Provides
    @Singleton
    fun provideGoalSet(preferences: Preferences): SetWeeklyGoalUseCase {
        return SetWeeklyGoalUseCase(preferences)
    }
}