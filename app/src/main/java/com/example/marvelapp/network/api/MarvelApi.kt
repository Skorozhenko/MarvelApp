package com.example.marvelapp.network.api

import com.example.marvelapp.data.model.dto.DtoHeroDataModel
import com.example.marvelapp.data.model.dto.DtoResultsModel
import com.example.marvelapp.data.model.entity.HeroEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getHeroesList(
        @Query("limit") limit: Long = 7
    ): DtoHeroDataModel

    @GET("v1/public/characters/{characterId}")
    suspend fun getHeroById(
        @Path("characterId") characterId: Int
    ): DtoHeroDataModel
}


