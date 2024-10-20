package com.roxx.grigarage.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beers")
data class BeerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val brand: String = "",
    val type: String = "",
    val alcoholPercentage: Double = 0.0,
    val volume: Float = 0f,
    val color: String = "",
    val rating: Double = 0.0,
    val notes: String = "",
    val photoUri: String = "",
    val dateAdded: Long = 0,
    val isFavorite: Boolean = false,
    val isWishlist: Boolean = false,
    val drinkCount: Int = 0
)
