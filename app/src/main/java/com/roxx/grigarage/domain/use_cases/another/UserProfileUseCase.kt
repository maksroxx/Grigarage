package com.roxx.grigarage.domain.use_cases.another

import com.roxx.grigarage.domain.model.UserProfile
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository

class UserProfileUseCase(
    private val preferences: Preferences,
    private val beerRepository: BeerRepository
) {

    suspend operator fun invoke(): UserProfile {
        val totalBeerCount = beerRepository.getTotalBeerCount() ?: 0
        val totalBottleCount = beerRepository.getTotalBottleCount() ?: 199
        val mostPopularBeer = beerRepository.getMostPopularBeer()?.name ?: "Unknown"
        val weeklyBottleCount = preferences.getCurrentWeekBeerCount()

        preferences.setTotalBottleCount(totalBottleCount)
        preferences.setTotalBeerCount(totalBeerCount)
        preferences.setMostPopularBeer(mostPopularBeer)

        return UserProfile(
            name = preferences.getUserName(),
            totalBeerCount = preferences.getTotalBeerCount(),
            totalBottleCount = preferences.getTotalBottleCount(),
            weeklyGoal = preferences.getWeeklyGoal(),
            currentWeekBeerCount = if (weeklyBottleCount < 0) 0 else weeklyBottleCount,
            mostPopularBeer = preferences.getMostPopularBeer()
        )
    }
}