package com.example.marvelapp.network

import com.example.marvelapp.data.mapper.HeroDtoToEntityMapper
import com.example.marvelapp.data.mapper.HeroEntityToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun provideDtoToEntityMapper(): HeroDtoToEntityMapper {
        return HeroDtoToEntityMapper()
    }

    @Provides
    @Singleton
    fun provideEntityToUiMapper(): HeroEntityToUiMapper {
        return HeroEntityToUiMapper()
    }
}