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
        val totalBottleCount = beerRepository.getTotalBottleCount() ?: 0
        val mostPopularBeer = beerRepository.getMostPopularBeer()?.name ?: "Unknown"

        preferences.setTotalBeerCount(totalBeerCount)
        preferences.setTotalBottleCount(totalBottleCount)
        preferences.setMostPopularBeer(mostPopularBeer)

        return UserProfile(
            name = preferences.getUserName(),
            totalBeerCount = preferences.getTotalBeerCount(),
            totalBottleCount = preferences.getTotalBottleCount(),
            weeklyGoal = preferences.getWeeklyGoal(),
            currentWeekBeerCount = preferences.getCurrentWeekBeerCount(),
            mostPopularBeer = preferences.getMostPopularBeer()
        )
    }
}