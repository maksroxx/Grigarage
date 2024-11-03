package com.roxx.grigarage

import com.roxx.grigarage.data.local.BeerDao
import com.roxx.grigarage.data.local.model.BeerEntity
import com.roxx.grigarage.data.mapper.toBeerEntity
import com.roxx.grigarage.data.mapper.toDomainModel
import com.roxx.grigarage.data.repository.BeerRepositoryImpl
import com.roxx.grigarage.domain.model.Beer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.util.Date

@ExperimentalCoroutinesApi
class BeerRepositoryTest {

    private lateinit var beerDao: BeerDao
    private lateinit var beerRepository: BeerRepositoryImpl

    @Before
    fun setUp() {
        beerDao = mockk(relaxed = true)
        beerRepository = BeerRepositoryImpl(beerDao)
    }

    @Test
    fun testInsertBeer() = runBlocking {
        val beer = Beer(name = "Test Beer")
        beerRepository.insertBeer(beer)

        coVerify {
            beerDao.insertBeer(withArg {
                assertEquals("Test Beer", it.name)
            })
        }
    }

    @Test
    fun testUpdateBeer() = runBlocking {
        val beer = Beer(id = 1, name = "Test Beer", isFavorite = false)
        beerRepository.updateBeer(beer)

        coVerify {
            beerDao.updateBeer(beer.toBeerEntity().copy(isFavorite = true))
        }
    }

    @Test
    fun testDeleteBeer() = runBlocking {
        val beer = Beer(name = "Test Beer")
        beerRepository.deleteBeer(beer)

        coVerify { beerDao.deleteBeer(beer.toBeerEntity()) }
    }

    @Test
    fun testGetBeerById() = runBlocking {
        val beerEntity =
            BeerEntity(id = 1, name = "Test Beer", dateAdded = Date.from(Instant.now()).time)
        coEvery { beerDao.getBeerById(1) } returns flowOf(beerEntity)

        val result = beerRepository.getBeerById(1).first()

        assertEquals(beerEntity.toDomainModel(), result)
    }

    @Test
    fun testIncrementDrinkCount() = runBlocking {
        val beerId = 1
        beerRepository.incrementDrinkCount(beerId)

        coVerify { beerDao.incrementDrinkCount(beerId) }
    }

    @Test
    fun testDecrementDrinkCount() = runBlocking {
        val beerId = 1
        beerRepository.decrementDrinkCount(beerId)

        coVerify { beerDao.decrementDrinkCount(beerId) }
    }

    @Test
    fun testGetMostPopularBeer() = runBlocking {
        val beerEntity = BeerEntity(
            id = 1,
            name = "Most Popular Beer",
            dateAdded = Date.from(Instant.now()).time
        )
        coEvery { beerDao.getMostPopularBeer() } returns beerEntity

        val result = beerRepository.getMostPopularBeer()

        assertEquals(beerEntity.toDomainModel(), result)
    }

    @Test
    fun testGetTotalBottleCount() = runBlocking {
        val totalBottles = 100
        coEvery { beerDao.getTotalBottleCount() } returns totalBottles

        val result = beerRepository.getTotalBottleCount()

        assertEquals(totalBottles, result)
    }

    @Test
    fun testGetTotalBeerCount() = runBlocking {
        val totalBeers = 200
        coEvery { beerDao.getTotalBeerCount() } returns totalBeers

        val result = beerRepository.getTotalBeerCount()

        assertEquals(totalBeers, result)
    }
}