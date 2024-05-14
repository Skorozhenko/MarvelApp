package com.example.marvelapp.data.repository

import android.util.Log
import com.example.marvelapp.data.local.dao.HeroDao
import com.example.marvelapp.data.mapper.HeroDtoToEntityMapper
import com.example.marvelapp.data.mapper.HeroEntityToUiMapper
import com.example.marvelapp.data.model.dto.DtoHeroDataModel
import com.example.marvelapp.data.model.ui.UiResultsModel
import com.example.marvelapp.network.api.MarvelApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi,
    private val dao: HeroDao,
    private val dtoToEntityMapper: HeroDtoToEntityMapper,
    private val entityToUiMapper: HeroEntityToUiMapper
) : MarvelRepository {

    override suspend fun getHeroesList(): DtoHeroDataModel {
        try {
            val heroesDto = api.getHeroesList()
            val entities = dtoToEntityMapper.map(heroesDto)
            entities.forEach { dao.insert(it) }
            return heroesDto
        } catch (e: Exception) {
            Log.e("MarvelRepositoryImpl", "Error getting heroes list", e)
            throw e
        }
    }

    override suspend fun getHeroById(characterId: Int): DtoHeroDataModel {
        try {
            val heroDto = api.getHeroById(characterId)
            val entity = dtoToEntityMapper.map(heroDto)
            entity.forEach { dao.insert(it) }
            return heroDto
        } catch (e: Exception) {
            Log.e("MarvelRepositoryImpl", "Error getting hero by ID: $characterId", e)
            throw e
        }
    }

    override suspend fun getLocalHeroesList(): List<UiResultsModel> {
        try {
            val entities = dao.getAllHeroes()
            return entityToUiMapper.map(entities)
        } catch (e: Exception) {
            Log.e("MarvelRepositoryImpl", "Error getting local heroes list", e)
            throw e
        }
    }

    override suspend fun getLocalHero(characterId: Int): UiResultsModel {
        try {
            val entity = dao.heroById(characterId)
            return entity.let { entityToUiMapper.map(it) }
        } catch (e: Exception) {
            Log.e("MarvelRepositoryImpl", "Error getting local hero by ID: $characterId", e)
            throw e
        }
    }
}