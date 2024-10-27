package com.roxx.grigarage.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.roxx.grigarage.data.local.BeerDao
import com.roxx.grigarage.data.mapper.toBeerEntity
import com.roxx.grigarage.data.mapper.toDomainModel
import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val db: BeerDao
): BeerRepository {
    override suspend fun insertBeer(beer: Beer) {
        db.insertBeer(beer.toBeerEntity())
    }

    override suspend fun updateBeer(beer: Beer) {
        val updatedFavoriteStatus = !beer.isFavorite
        val updatedBeerEntity = beer.toBeerEntity().copy(isFavorite = updatedFavoriteStatus)
        db.updateBeer(updatedBeerEntity)
        Log.d("Update", "Updated favorite status for ${updatedBeerEntity.id}: ${updatedFavoriteStatus}")
    }

    override suspend fun deleteBeer(beer: Beer) {
        db.deleteBeer(beer.toBeerEntity())
    }

    override suspend fun getBeerById(beerId: Int): Beer? {
        return db.getBeerById(beerId)?.toDomainModel()
    }

    override suspend fun incrementDrinkCount(beerId: Int) {
        db.incrementDrinkCount(beerId)
    }

    override suspend fun decrementDrinkCount(beerId: Int) {
        db.decrementDrinkCount(beerId)
    }

    override suspend fun getMostPopularBeer(): Beer? {
        return db.getMostPopularBeer()?.toDomainModel()
    }

    override suspend fun getTotalBottleCount(): Int? {
        return db.getTotalBottleCount()
    }

    override suspend fun getTotalBeerCount(): Int? {
        return db.getTotalBeerCount()
    }

    override fun searchPagedBeers(query: String): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = true),
            pagingSourceFactory = { db.searchPagedBeers(query.lowercase()) }
        ).flow.map { pagingData ->
            pagingData.map { beerEntity ->
                Log.d("Search", "search: ${beerEntity.id}")
                beerEntity.toDomainModel()
            }
        }
    }

    override fun getPagedBeers(): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = true),
            pagingSourceFactory = { db.getPagedBeers() }
        ).flow.map { pagingData ->
            pagingData.map { beerEntity ->
                beerEntity.toDomainModel()
            }
        }
    }

    override fun getPagedFavoriteBeers(): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = true),
            pagingSourceFactory = { db.getPagedFavoriteBeers() }
        ).flow.map { pagingData ->
            pagingData.map { beerEntity ->
                beerEntity.toDomainModel()
            }
        }
    }
}