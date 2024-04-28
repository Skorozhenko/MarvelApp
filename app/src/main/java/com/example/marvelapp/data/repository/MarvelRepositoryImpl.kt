package com.example.marvelapp.data.repository

import com.example.marvelapp.network.api.MarvelApi
import com.example.marvelapp.ui.components.HeroDataModel
import com.example.marvelapp.ui.components.ResultsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : MarvelRepository {
    override suspend fun getHeroesList(): HeroDataModel = api.getHeroesList()

    override suspend fun getHeroById(characterId: Int): HeroDataModel =
        api.getHeroById(characterId = characterId)
}