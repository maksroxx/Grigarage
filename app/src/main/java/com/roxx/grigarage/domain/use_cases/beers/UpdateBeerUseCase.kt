package com.roxx.grigarage.domain.use_cases.beers

import android.util.Log
import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.repository.BeerRepository

class UpdateBeerUseCase(private val beerRepository: BeerRepository) {

    suspend operator fun invoke(beer: Beer) {
        beerRepository.updateBeer(beer)
        // Log.d("Update", "use case ${beer.id} ${beer.isFavorite}")
    }
}