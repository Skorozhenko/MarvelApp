package com.example.marvelapp.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class DtoHeroDataModel(
    val code: Int,
    val status: String,
    val data: DtoDataModel
)
