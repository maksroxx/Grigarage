package com.roxx.grigarage.di

import com.roxx.grigarage.domain.use_cases.another.ConvertBase64ToImageBitmapUseCase
import com.roxx.grigarage.domain.use_cases.another.ConvertBitmapToBase64UseCase
import com.roxx.grigarage.domain.use_cases.another.FilterOutDigit
import com.roxx.grigarage.domain.use_cases.another.FilterOutLetter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilityUseCasesModule {
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
}