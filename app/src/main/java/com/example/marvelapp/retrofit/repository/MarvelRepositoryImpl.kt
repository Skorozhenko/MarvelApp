package com.example.marvelapp.retrofit.repository

import com.example.marvelapp.retrofit.api.MarvelApi
import com.example.marvelapp.screens.components.HeroDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : MarvelRepository {
    override suspend fun getHeroesList(): HeroDataModel = api.getHeroesList()

    override suspend fun getHeroById(characterId: Int): HeroDataModel = api.getHeroById(characterId = characterId)
}