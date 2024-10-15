package com.roxx.grigarage.domain.use_cases.beers

import androidx.paging.PagingData
import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class GetAllBeersUseCase(
    private val beerRepository: BeerRepository
) {

    operator fun invoke(): Flow<PagingData<Beer>> {
        return beerRepository.getPagedBeers()
    }
}