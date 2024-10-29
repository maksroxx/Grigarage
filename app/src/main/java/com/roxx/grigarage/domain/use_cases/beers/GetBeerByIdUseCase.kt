package com.roxx.grigarage.domain.use_cases.beers

import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class GetBeerByIdUseCase(private val beerRepository: BeerRepository) {

    suspend operator fun invoke(beerId: Int): Flow<Beer?> {
        return beerRepository.getBeerById(beerId)
    }
}