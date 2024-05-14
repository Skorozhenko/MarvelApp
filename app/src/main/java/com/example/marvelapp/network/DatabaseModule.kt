package com.example.marvelapp.network

import android.content.Context
import androidx.room.Room
import com.example.marvelapp.data.local.AppDatabase
import com.example.marvelapp.data.local.dao.HeroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun database(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "heroes-database"
        )
            .build()

    @Provides
    fun heroDao(database: AppDatabase): HeroDao =
        database.heroDao()
}