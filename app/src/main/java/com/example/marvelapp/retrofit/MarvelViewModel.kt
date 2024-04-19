package com.example.marvelapp.retrofit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.retrofit.repository.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarvelViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository,
) : ViewModel() {

    private val _heroDataModel = MutableStateFlow(HeroModelState())
    val heroDataModel: StateFlow<HeroModelState> = _heroDataModel

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        getHeroesList()
    }

    private fun getHeroesList() {
        viewModelScope.launch {
            try {
                _heroDataModel.value = _heroDataModel.value.copy(isLoading = true)
                val heroData = marvelRepository.getHeroesList()
                _heroDataModel.value = _heroDataModel.value.copy(heroModel = heroData, isLoading = false)
            } catch (e: Exception) {
                _heroDataModel.value = _heroDataModel.value.copy(heroModel = null, isLoading = false)
                _errorMessage.value = "Error fetching heroes: ${e.message}"
                Log.e("MarvelViewModel", "Error fetching heroes", e)
            }
        }
    }
}