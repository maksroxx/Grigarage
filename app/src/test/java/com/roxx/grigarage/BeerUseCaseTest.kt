package com.roxx.grigarage

import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository
import com.roxx.grigarage.domain.use_cases.beers.DecrementDrinkCountUseCase
import com.roxx.grigarage.domain.use_cases.beers.DeleteBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetBeerByIdUseCase
import com.roxx.grigarage.domain.use_cases.beers.IncrementDrinkCountUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BeerUseCaseTest {

    private val beerRepository: BeerRepository = mockk()
    private val preferences: Preferences = mockk()
    private val decrementDrinkCountUseCase = DecrementDrinkCountUseCase(beerRepository, preferences)
    private val incrementDrinkCountUseCase = IncrementDrinkCountUseCase(beerRepository, preferences)
    private val deleteBeerUseCase = DeleteBeerUseCase(beerRepository)
    private val getBeerByIdUseCase = GetBeerByIdUseCase(beerRepository)

    @Test
    fun `should decrement current week beer count and decrement drink count in repository`(): Unit = runBlocking {
        every { preferences.getCurrentWeekBeerCount() } returns 5
        every { preferences.setCurrentWeekBeerCount(4) } just Runs
        coEvery { beerRepository.decrementDrinkCount(1) } just Runs

        decrementDrinkCountUseCase(1)

        verify { preferences.setCurrentWeekBeerCount(4) }
        coEvery { beerRepository.decrementDrinkCount(1) }
    }

    @Test
    fun `should increment current week beer count and increment drink count in repository`(): Unit = runBlocking {
        every { preferences.getCurrentWeekBeerCount() } returns 5
        every { preferences.setCurrentWeekBeerCount(6) } just Runs
        coEvery { beerRepository.incrementDrinkCount(1) } just Runs

        incrementDrinkCountUseCase(1)

        verify { preferences.setCurrentWeekBeerCount(6) }
        coEvery { beerRepository.incrementDrinkCount(1) }
    }

    @Test
    fun `should delete beer from repository`() = runBlocking {
        val beer = Beer(id = 1, name = "Test Beer")
        coEvery { beerRepository.deleteBeer(beer) } just Runs

        deleteBeerUseCase(beer)
        coVerify { beerRepository.deleteBeer(beer) }
    }

    @Test
    fun `should return beer from repository by id`() = runBlocking {
        val beerId = 1
        val beer = Beer(id = beerId, name = "Test Beer")
        coEvery { beerRepository.getBeerById(beerId) } returns flowOf(beer)

        val result = getBeerByIdUseCase(beerId).toList()
        assertEquals(beer, result[0])
    }

    @Test
    fun `should return null when beer not found`() = runBlocking {
        val beerId = 1
        coEvery { beerRepository.getBeerById(beerId) } returns flowOf(null)

        val result = getBeerByIdUseCase(beerId).toList()
        assertEquals(null, result[0])
    }
}