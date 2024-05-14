package com.example.marvelapp.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class DtoResultsModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: DtoThumbnailModel
)
