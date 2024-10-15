package com.roxx.grigarage.domain.use_cases.beers

import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.repository.BeerRepository

class GetBeerByIdUseCase(private val beerRepository: BeerRepository) {

    suspend operator fun invoke(beerId: Int): Beer? {
        return beerRepository.getBeerById(beerId)
    }
}