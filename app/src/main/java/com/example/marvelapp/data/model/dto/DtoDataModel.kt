package com.example.marvelapp.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class DtoDataModel(
    val results: List<DtoResultsModel>
)
