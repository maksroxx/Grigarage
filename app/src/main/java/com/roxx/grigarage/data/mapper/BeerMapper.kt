package com.roxx.grigarage.data.mapper

import com.roxx.grigarage.data.local.model.BeerEntity
import com.roxx.grigarage.domain.model.Beer

fun BeerEntity.toDomainModel(): Beer {
    return Beer(
        id = id,
        name = name,
        brand = brand,
        type = type,
        alcoholPercentage = alcoholPercentage,
        volume = volume,
        rating = rating,
        notes = notes,
        photoUri = photoUri,
        isFavorite = isFavorite,
        drinkCount = drinkCount,
        dateAdded = dateAdded
    )
}

fun Beer.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        brand = brand,
        type = type,
        alcoholPercentage = alcoholPercentage,
        volume = volume,
        rating = rating,
        notes = notes,
        photoUri = photoUri,
        isFavorite = isFavorite,
        drinkCount = drinkCount,
        dateAdded = dateAdded
    )
}