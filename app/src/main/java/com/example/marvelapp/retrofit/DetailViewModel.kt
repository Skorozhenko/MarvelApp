package com.example.marvelapp.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.retrofit.repository.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val marvelRepository: MarvelRepository
) : ViewModel() {

    private val _heroDataModel = MutableStateFlow(HeroModelState())
    val heroDataModel: StateFlow<HeroModelState> = _heroDataModel

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun getHeroById(characterId: Int) {
        viewModelScope.launch {
            try {
                _heroDataModel.value = _heroDataModel.value.copy(isLoading = true)
                val heroData = marvelRepository.getHeroById(characterId)
                _heroDataModel.value = _heroDataModel.value.copy(heroModel = heroData, isLoading = false)
            } catch (e: Exception) {
                _heroDataModel.value = _heroDataModel.value.copy(heroModel = null, isLoading = false)
                _errorMessage.value = "Error fetching hero: ${e.message}"
            }
        }
    }
}