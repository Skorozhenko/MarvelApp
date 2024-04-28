package com.example.marvelapp.data.repository

import com.example.marvelapp.ui.components.HeroDataModel
import com.example.marvelapp.ui.components.ResultsModel


interface MarvelRepository {
    suspend fun getHeroesList(): HeroDataModel

    suspend fun getHeroById(characterId: Int): HeroDataModel
}