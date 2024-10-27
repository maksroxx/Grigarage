package com.roxx.grigarage.domain.use_cases.beers

import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository

class DecrementDrinkCountUseCase(private val beerRepository: BeerRepository, private val preferences: Preferences) {

    suspend operator fun invoke(beerId: Int) {
        val bottles = preferences.getCurrentWeekBeerCount().dec()
        preferences.setCurrentWeekBeerCount(bottles)
        beerRepository.incrementDrinkCount(beerId)
    }
}