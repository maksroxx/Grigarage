package com.roxx.grigarage.di

import android.content.Context
import androidx.room.Room
import com.roxx.grigarage.data.local.BeerDao
import com.roxx.grigarage.data.local.GrigarageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): GrigarageDatabase {
        return Room.databaseBuilder(
            context,
            GrigarageDatabase::class.java,
            "grigarage_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerDao(db: GrigarageDatabase): BeerDao {
        return db.beerDao()
    }
}