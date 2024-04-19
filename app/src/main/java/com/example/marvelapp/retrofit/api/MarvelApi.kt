package com.example.marvelapp.retrofit.api

import com.example.marvelapp.screens.components.HeroDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getHeroesList(
        @Query("limit") limit: Long = 7,
        //@Query("apikey") apiKey: String = "24443ee7c47f138c9773b5e6cef4c2c7"
    ) : HeroDataModel

    @GET("v1/public/characters/{characterId}")
    suspend fun getHeroById(
        @Path("characterId") characterId: Int
    ) : HeroDataModel
}


