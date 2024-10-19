package com.roxx.grigarage.domain.model

data class Beer(
    val name: String,
    val brand: String = "",
    val type: String = "",
    val alcoholPercentage: Double = 0.0,
    val volume: Float = 0f,
    val color: String = "",
    val rating: Float = 0f,
    val notes: String = "",
    val photoUri: String = "",
    val dateAdded: Long = 0,
    val isFavorite: Boolean = false,
    val isWishlist: Boolean = false,
    val drinkCount: Int = 0
)
