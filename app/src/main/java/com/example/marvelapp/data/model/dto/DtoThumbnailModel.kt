package com.example.marvelapp.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class DtoThumbnailModel(
    val path: String,
    val extension: String
)