package com.roxx.grigarage.presentation.util

import androidx.compose.ui.graphics.ImageBitmap
import com.roxx.grigarage.domain.model.Beer
import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel

fun Beer.toBeerUiModel(bitmap: ImageBitmap): BeerUiModel {
    return BeerUiModel(
        id = id,
        name = name,
        brand = brand,
        type = type,
        alcoholPercentage = alcoholPercentage,
        volume = volume,
        rating = rating,
        notes = notes,
        photoUri = bitmap,
        dateAdded = formatDate(dateAdded),
        isFavorite = isFavorite,
        drinkCount = drinkCount
    )
}

fun BeerUiModel.toBeer(photo: String): Beer {
    return Beer(
        id = id,
        name = name,
        brand = brand,
        type = type,
        alcoholPercentage = alcoholPercentage,
        volume = volume,
        rating = rating,
        notes = notes,
        photoUri = photo,
        dateAdded = parseDateToTimestamp(dateAdded),
        isFavorite = isFavorite,
        drinkCount = drinkCount
    )
}