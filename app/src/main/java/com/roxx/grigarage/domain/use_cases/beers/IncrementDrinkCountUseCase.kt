package com.roxx.grigarage.domain.use_cases.beers

import com.roxx.grigarage.domain.repository.BeerRepository

class IncrementDrinkCountUseCase(private val beerRepository: BeerRepository) {

    suspend operator fun invoke(beerId: Int) {
        beerRepository.incrementDrinkCount(beerId)
    }
}