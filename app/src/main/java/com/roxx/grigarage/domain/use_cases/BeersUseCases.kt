package com.roxx.grigarage.domain.use_cases

import com.roxx.grigarage.domain.use_cases.beers.DecrementDrinkCountUseCase
import com.roxx.grigarage.domain.use_cases.beers.DeleteBeerUseCase
import com.roxx.grigarage.domain.use_cases.beers.GetBeerByIdUseCase
import com.roxx.grigarage.domain.use_cases.beers.IncrementDrinkCountUseCase

data class BeersUseCases(
    val decrementDrinkCountUseCase: DecrementDrinkCountUseCase,
    val incrementDrinkCountUseCase: IncrementDrinkCountUseCase,
    val deleteBeerUseCase: DeleteBeerUseCase,
    val getBeerByIdUseCase: GetBeerByIdUseCase
)