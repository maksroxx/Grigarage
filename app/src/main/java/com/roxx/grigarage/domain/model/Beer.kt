package com.roxx.grigarage.domain.model

data class Beer(
    val name: String,
    val brand: String,
    val type: String,
    val alcoholPercentage: Double,
    val volume: Float,
    val color: String,
    val rating: Float,
    val notes: String,
    val photoUri: String,
    val dateAdded: Long,
    val isFavorite: Boolean,
    val isWishlist: Boolean,
    val drinkCount: Int
)
