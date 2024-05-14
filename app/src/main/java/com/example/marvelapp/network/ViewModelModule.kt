package com.example.marvelapp.network

import com.example.marvelapp.data.local.dao.HeroDao
import com.example.marvelapp.data.mapper.HeroDtoToEntityMapper
import com.example.marvelapp.data.mapper.HeroEntityToUiMapper
import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.ui.screens.detail.DetailViewModel
import com.example.marvelapp.ui.screens.marvel.MarvelViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMarvelViewModel(
        marvelRepository: MarvelRepository,
        heroDao: HeroDao,
        dtoToEntityMapper: HeroDtoToEntityMapper,
        entityToUiMapper: HeroEntityToUiMapper
    ): MarvelViewModel {
        return MarvelViewModel(marvelRepository, heroDao, dtoToEntityMapper, entityToUiMapper)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailViewModel(
        marvelRepository: MarvelRepository,
        heroDao: HeroDao,
        dtoToEntityMapper: HeroDtoToEntityMapper,
        entityToUiMapper: HeroEntityToUiMapper
    ): DetailViewModel {
        return DetailViewModel(marvelRepository, heroDao, dtoToEntityMapper, entityToUiMapper)
    }
}