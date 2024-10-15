package com.roxx.grigarage.di

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository
import com.roxx.grigarage.domain.use_cases.BeersUseCases
import com.roxx.grigarage.domain.use_cases.another.ConvertBase64ToImageBitmapUseCase
import com.roxx.grigarage.domain.use_cases.another.FilterOutDigit
import com.roxx.grigarage.domain.use_cases.another.FilterOutLetter
import com.roxx.grigarage.domain.use_cases.another.TakePhotoUseCase
import com.roxx.grigarage.domain.use_cases.another.UserProfileUseCase
import com.roxx.grigarage.domain.use_cases.beers.DecrementDrinkCountUseCase
import com.roxx.grigarage.domain.use_cases.beers.DeleteBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetAllBeersUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetBeerByIdUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetLikedBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetMostPopularBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetWishListUseCase
import com.roxx.grigarage.domain.use_cases.beers.IncrementDrinkCountUseCase
import com.roxx.grigarage.domain.use_cases.beers.InsertOrUpdateBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.SearchBeersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUserProfileUseCase(
        preferences: Preferences,
        repository: BeerRepository
    ): UserProfileUseCase {
        return UserProfileUseCase(preferences, repository)
    }

    @Provides
    @Singleton
    fun provideBeerUseCases(beerRepository: BeerRepository): BeersUseCases {
        return BeersUseCases(
            decrementDrinkCountUseCase = DecrementDrinkCountUseCase(beerRepository),
            insertOrUpdateBeerUseCase = InsertOrUpdateBeerUseCase(beerRepository),
            deleteBeerUseCase = DeleteBeerUseCase(beerRepository),
            incrementDrinkCountUseCase = IncrementDrinkCountUseCase(beerRepository),
            searchBeersUseCase = SearchBeersUseCase(beerRepository),
            getBeerByIdUseCase = GetBeerByIdUseCase(beerRepository),
            getMostPopularBeerUseCase = GetMostPopularBeerUseCase(beerRepository),
            getLikedBeerUseCase = GetLikedBeerUseCase(beerRepository),
            getAllBeersUseCase = GetAllBeersUseCase(beerRepository),
            getWishListUseCase = GetWishListUseCase(beerRepository)
        )
    }

    @Provides
    @Singleton
    fun provideFilterOutLetterUseCase(): FilterOutLetter {
        return FilterOutLetter()
    }

    @Provides
    @Singleton
    fun provideFilerOutDigitUseCase(): FilterOutDigit {
        return FilterOutDigit()
    }

    @Provides
    @Singleton
    fun BaseToImage(): ConvertBase64ToImageBitmapUseCase {
        return ConvertBase64ToImageBitmapUseCase()
    }

    @Provides
    @Singleton
    fun BitmapToBase(): ConvertBase64ToImageBitmapUseCase {
        return ConvertBase64ToImageBitmapUseCase()
    }

    @Provides
    @Singleton
    fun provideTakePhotoUseCase(
        cameraController: LifecycleCameraController,
        @ApplicationContext context: Context
    ): TakePhotoUseCase {
        return TakePhotoUseCase(cameraController, context)
    }
}