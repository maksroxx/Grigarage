package com.roxx.grigarage.di

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository
import com.roxx.grigarage.domain.use_cases.BeersUseCases
import com.roxx.grigarage.domain.use_cases.another.ConvertBase64ToImageBitmapUseCase
import com.roxx.grigarage.domain.use_cases.another.ConvertBitmapToBase64UseCase
import com.roxx.grigarage.domain.use_cases.another.FilterOutDigit
import com.roxx.grigarage.domain.use_cases.another.FilterOutLetter
import com.roxx.grigarage.domain.use_cases.another.SetWeeklyGoalUseCase
import com.roxx.grigarage.domain.use_cases.another.TakePhotoUseCase
import com.roxx.grigarage.domain.use_cases.another.UserProfileUseCase
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
    fun baseToImage(): ConvertBase64ToImageBitmapUseCase {
        return ConvertBase64ToImageBitmapUseCase()
    }

    @Provides
    @Singleton
    fun bitmapToBase(): ConvertBitmapToBase64UseCase {
        return ConvertBitmapToBase64UseCase()
    }

    @Provides
    @Singleton
    fun provideTakePhotoUseCase(
        cameraController: LifecycleCameraController,
        @ApplicationContext context: Context
    ): TakePhotoUseCase {
        return TakePhotoUseCase(cameraController, context)
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
    fun provideGoalSet(preferences: Preferences): SetWeeklyGoalUseCase {
        return SetWeeklyGoalUseCase(preferences)
    }

    @Provides
    @Singleton
    fun provideLikedBeers(beerRepository: BeerRepository): GetLikedBeerUseCase {
        return GetLikedBeerUseCase(beerRepository)
    }
}