package com.roxx.grigarage.di

import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository
import com.roxx.grigarage.domain.use_cases.BeersUseCases
import com.roxx.grigarage.domain.use_cases.beers.DecrementDrinkCountUseCase
import com.roxx.grigarage.domain.use_cases.beers.DeleteBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetAllBeersUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetBeerByIdUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetLikedBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.IncrementDrinkCountUseCase
import com.roxx.grigarage.domain.use_cases.beers.InsertBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.SearchBeersUseCase
import com.roxx.grigarage.domain.use_cases.beers.UpdateBeerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideBeerUseCases(beerRepository: BeerRepository, preferences: Preferences): BeersUseCases {
        return BeersUseCases(
            decrementDrinkCountUseCase = DecrementDrinkCountUseCase(beerRepository, preferences),
            deleteBeerUseCase = DeleteBeerUseCase(beerRepository),
            incrementDrinkCountUseCase = IncrementDrinkCountUseCase(beerRepository, preferences),
            getBeerByIdUseCase = GetBeerByIdUseCase(beerRepository)
        )
    }

    @Provides
    @Singleton
    fun providePagedBeers(beerRepository: BeerRepository): GetAllBeersUseCase {
        return GetAllBeersUseCase(beerRepository)
    }

    @Provides
    @Singleton
    fun provideInsertUpdate(beerRepository: BeerRepository, preferences: Preferences): InsertBeerUseCase {
        return InsertBeerUseCase(beerRepository, preferences)
    }

    @Provides
    @Singleton
    fun provideUpdate(beerRepository: BeerRepository): UpdateBeerUseCase {
        return UpdateBeerUseCase(beerRepository)
    }

    @Provides
    @Singleton
    fun provideSearch(beerRepository: BeerRepository): SearchBeersUseCase {
        return SearchBeersUseCase(beerRepository)
    }

    @Provides
    @Singleton
    fun provideLikedBeers(beerRepository: BeerRepository): GetLikedBeerUseCase {
        return GetLikedBeerUseCase(beerRepository)
    }
}