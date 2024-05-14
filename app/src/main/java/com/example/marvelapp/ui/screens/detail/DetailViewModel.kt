package com.example.marvelapp.ui.screens.detail

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
class DetailViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository,
) : ViewModel() {

    private val _status = MutableLiveData<MarvelApiStatus>()
    val status: LiveData<MarvelApiStatus> = _status

    private val _heroDataModel = MutableLiveData<HeroDataModel>()
    val heroDataModel: LiveData<HeroDataModel> = _heroDataModel


    fun getHeroById(heroId: Int) {
        viewModelScope.launch {
            try {
                _status.value = MarvelApiStatus.LOADING
                val heroData = marvelRepository.getHeroById(heroId)
                _heroDataModel.value = heroData
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarvelApiStatus.ERROR
                Log.e("DetailViewModel", "Error fetching hero data", e)
            }
        }
    }
}