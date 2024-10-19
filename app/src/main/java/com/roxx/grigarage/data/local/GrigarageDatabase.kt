package com.roxx.grigarage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roxx.grigarage.data.local.model.BeerEntity

@Database(
    entities = [BeerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GrigarageDatabase: RoomDatabase() {

    abstract fun beerDao(): BeerDao
}