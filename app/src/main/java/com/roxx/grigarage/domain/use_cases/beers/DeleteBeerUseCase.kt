package com.roxx.grigarage.domain.use_cases.beers

import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.repository.BeerRepository

class DeleteBeerUseCase(private val beerRepository: BeerRepository) {

    suspend operator fun invoke(beer: Beer) {
        beerRepository.deleteBeer(beer)
    }
}
