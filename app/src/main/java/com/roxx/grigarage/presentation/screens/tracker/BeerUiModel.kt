package com.roxx.grigarage.presentation.screens.tracker

import androidx.compose.ui.graphics.ImageBitmap

data class BeerUiModel(
    val id: Int = 0,
    val name: String,
    val brand: String = "",
    val type: String = "",
    val alcoholPercentage: Float = 0f,
    val volume: Float = 0f,
    val color: String = "",
    val rating: Float = 0f,
    val notes: String = "",
    val photoUri: ImageBitmap,
    val dateAdded: String,
    val isFavorite: Boolean = false,
    val drinkCount: Int = 1
)