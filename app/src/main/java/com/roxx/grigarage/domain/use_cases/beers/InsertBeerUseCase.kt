package com.roxx.grigarage.domain.use_cases.beers

import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository

class InsertBeerUseCase(private val beerRepository: BeerRepository, private val preferences: Preferences) {

    suspend operator fun invoke(beer: Beer) {
        val bottles = preferences.getCurrentWeekBeerCount().inc()
        preferences.setCurrentWeekBeerCount(bottles)
        beerRepository.insertBeer(beer)
    }
}