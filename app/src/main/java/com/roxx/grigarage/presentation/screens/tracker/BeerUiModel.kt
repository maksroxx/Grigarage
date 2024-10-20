package com.roxx.grigarage.presentation.screens.tracker

import androidx.compose.ui.graphics.ImageBitmap

data class BeerUiModel(
    val name: String,
    val brand: String = "",
    val type: String = "",
    val alcoholPercentage: Double = 0.0,
    val volume: Float = 0f,
    val color: String = "",
    val rating: Double = 0.0,
    val notes: String = "",
    val photoUri: ImageBitmap,
    val dateAdded: Long = 0,
    val isFavorite: Boolean = false,
    val isWishlist: Boolean = false,
    val drinkCount: Int = 0
)
