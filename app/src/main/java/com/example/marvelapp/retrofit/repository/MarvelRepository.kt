package com.example.marvelapp.retrofit.repository

import com.example.marvelapp.screens.components.HeroDataModel


interface MarvelRepository {
    suspend fun getHeroesList(): HeroDataModel

    suspend fun getHeroById(characterId: Int): HeroDataModel
}