package com.example.marvelapp.retrofit

import com.example.marvelapp.screens.components.HeroDataModel

data class HeroModelState(
    val heroModel: HeroDataModel? = null,
    val isLoading: Boolean = false
)