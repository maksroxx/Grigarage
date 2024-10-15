package com.roxx.grigarage.domain.use_cases

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

data class BeersUseCases(
    val decrementDrinkCountUseCase: DecrementDrinkCountUseCase,
    val incrementDrinkCountUseCase: IncrementDrinkCountUseCase,
    val deleteBeerUseCase: DeleteBeerUseCase,
    val insertOrUpdateBeerUseCase: InsertOrUpdateBeerUseCase,
    val getBeerByIdUseCase: GetBeerByIdUseCase,
    val getMostPopularBeerUseCase: GetMostPopularBeerUseCase,

    // paging data
    val searchBeersUseCase: SearchBeersUseCase,
    val getAllBeersUseCase: GetAllBeersUseCase,
    val getLikedBeerUseCase: GetLikedBeerUseCase,
    val getWishListUseCase: GetWishListUseCase
)