package com.roxx.grigarage.domain.model

data class UserProfile(
    val name: String,
    val totalBeerCount: Int,
    val totalBottleCount: Int,
    val weeklyGoal: Int,
    val currentWeekBeerCount: Int,
    val mostPopularBeer: String
)
