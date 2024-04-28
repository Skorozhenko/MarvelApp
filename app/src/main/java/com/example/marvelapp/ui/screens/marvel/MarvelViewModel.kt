package com.example.marvelapp.ui.screens.marvel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.ui.components.HeroDataModel
import com.example.marvelapp.ui.components.MarvelApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository,
) : ViewModel() {
    private val _status = MutableLiveData<MarvelApiStatus>()
    val status: LiveData<MarvelApiStatus> = _status

    private val _heroesDataModel = MutableLiveData<HeroDataModel>()
    val heroesDataModel: LiveData<HeroDataModel> = _heroesDataModel

    init {
        getHeroesList()
    }

    fun getHeroesList() {
        viewModelScope.launch {
            try {
                _status.value = MarvelApiStatus.LOADING
                val heroData = marvelRepository.getHeroesList()
                _heroesDataModel.value = heroData
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarvelApiStatus.ERROR
                Log.e("MarvelViewModel", "Error fetching heroes", e)
            }
        }
    }
}