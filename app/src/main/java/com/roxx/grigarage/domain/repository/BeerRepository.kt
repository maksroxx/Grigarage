package com.roxx.grigarage.domain.repository

import androidx.paging.PagingData
import com.roxx.grigarage.domain.model.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    suspend fun insertBeer(beer: Beer)
    suspend fun updateBeer(beer: Beer)
    suspend fun deleteBeer(beer: Beer)
    suspend fun getBeerById(beerId: Int): Beer?
    suspend fun incrementDrinkCount(beerId: Int)
    suspend fun decrementDrinkCount(beerId: Int)
    suspend fun getMostPopularBeer(): Beer?
    suspend fun getTotalBottleCount(): Int?
    suspend fun getTotalBeerCount(): Int?

    fun searchPagedBeers(query: String): Flow<PagingData<Beer>>
    fun getPagedBeers(): Flow<PagingData<Beer>>
    fun getPagedFavoriteBeers(): Flow<PagingData<Beer>>
}
