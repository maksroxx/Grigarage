package com.roxx.grigarage.data.mapper

import com.roxx.grigarage.data.local.model.BeerEntity
import com.roxx.grigarage.domain.model.Beer

fun BeerEntity.toDomainModel(): Beer {
    return Beer(
        name = name,
        brand = brand,
        type = type,
        alcoholPercentage = alcoholPercentage,
        volume = volume,
        color = color,
        rating = rating,
        notes = notes,
        photoUri = photoUri,
        isFavorite = isFavorite,
        isWishlist = isWishlist,
        drinkCount = drinkCount,
        dateAdded = dateAdded
    )
}

fun Beer.toBeerEntity(): BeerEntity {
    return BeerEntity(
        name = name,
        brand = brand,
        type = type,
        alcoholPercentage = alcoholPercentage,
        volume = volume,
        color = color,
        rating = rating,
        notes = notes,
        photoUri = photoUri,
        isFavorite = isFavorite,
        isWishlist = isWishlist,
        drinkCount = drinkCount,
        dateAdded = dateAdded
    )
}