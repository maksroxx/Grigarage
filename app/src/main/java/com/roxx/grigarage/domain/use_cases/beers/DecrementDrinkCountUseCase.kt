package com.roxx.grigarage.domain.use_cases.beers

import android.util.Log
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository

class DecrementDrinkCountUseCase(
    private val beerRepository: BeerRepository,
    private val preferences: Preferences
) {

    suspend operator fun invoke(beerId: Int) {
        val bottles = preferences.getCurrentWeekBeerCount().dec()
        Log.d("Total bottle count", "New count: ${preferences.getTotalBottleCount()}")
        preferences.setCurrentWeekBeerCount(bottles)
        beerRepository.decrementDrinkCount(beerId)
    }
}