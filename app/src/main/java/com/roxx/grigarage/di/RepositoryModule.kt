package com.roxx.grigarage.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.roxx.grigarage.data.local.BeerDao
import com.roxx.grigarage.data.local.GrigarageDatabase
import com.roxx.grigarage.data.preferences.PreferencesImpl
import com.roxx.grigarage.data.repository.BeerRepositoryImpl
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.repository.BeerRepository
import com.roxx.grigarage.domain.use_cases.BeersUseCases
import com.roxx.grigarage.domain.use_cases.another.FilterOutLetter
import com.roxx.grigarage.domain.use_cases.another.UserProfileUseCase
import com.roxx.grigarage.domain.use_cases.beers.DecrementDrinkCountUseCase
import com.roxx.grigarage.domain.use_cases.beers.DeleteBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetBeerByIdUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetMostPopularBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.IncrementDrinkCountUseCase
import com.roxx.grigarage.domain.use_cases.beers.InsertOrUpdateBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.SearchBeersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBeerRepository(db: BeerDao): BeerRepository {
        return BeerRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(
        sharedPreferences: SharedPreferences
    ): Preferences {
        return PreferencesImpl(sharedPreferences)
    }
}