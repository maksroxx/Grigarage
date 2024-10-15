package com.roxx.grigarage.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beers")
data class BeerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
    val isFavorite: Boolean = false,
    val isWishlist: Boolean = false,
    val drinkCount: Int = 0
)
