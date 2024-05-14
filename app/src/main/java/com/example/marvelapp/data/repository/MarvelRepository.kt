package com.example.marvelapp.data.repository

import com.example.marvelapp.data.model.dto.DtoHeroDataModel
import com.example.marvelapp.data.model.dto.DtoResultsModel
import com.example.marvelapp.data.model.ui.UiResultsModel


interface MarvelRepository {
    suspend fun getHeroesList(): DtoHeroDataModel

    suspend fun getHeroById(characterId: Int): DtoHeroDataModel

    suspend fun getLocalHeroesList(): List<UiResultsModel>

    suspend fun getLocalHero(characterId: Int): UiResultsModel
}