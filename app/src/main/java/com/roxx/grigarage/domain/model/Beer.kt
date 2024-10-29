package com.roxx.grigarage.domain.model

data class Beer(
    val id: Int = 0,
    val name: String,
    val brand: String = "",
    val type: String = "",
    val alcoholPercentage: Float = 0f,
    val volume: Float = 0f,
    val rating: Float = 0f,
    val notes: String = "",
    val photoUri: String = "",
    val dateAdded: Long = 0,
    val isFavorite: Boolean = false,
    val drinkCount: Int = 1
)
