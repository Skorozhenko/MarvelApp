package com.example.marvelapp.ui.screens.marvel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.local.dao.HeroDao
import com.example.marvelapp.data.mapper.HeroDtoToEntityMapper
import com.example.marvelapp.data.mapper.HeroEntityToUiMapper
import com.example.marvelapp.data.model.dto.DtoHeroDataModel
import com.example.marvelapp.data.model.ui.UiDataModel
import com.example.marvelapp.data.model.ui.UiHeroDataModel
import com.example.marvelapp.data.model.ui.UiResultsModel
import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.ui.components.MarvelApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository,
    private val heroDao: HeroDao,
    private val dtoToEntityMapper: HeroDtoToEntityMapper,
    private val entityToUiMapper: HeroEntityToUiMapper
) : ViewModel() {

    private val _status = MutableLiveData<MarvelApiStatus>()
    val status: LiveData<MarvelApiStatus> = _status

    private val _heroesDataModel = MutableLiveData<List<UiResultsModel>>()
    val heroesDataModel: LiveData<List<UiResultsModel>> = _heroesDataModel

    init {
        getHeroesList()
    }

    fun getHeroesList() {
        viewModelScope.launch {
            try {
                _status.value = MarvelApiStatus.LOADING
                val localHeroes = heroDao.getAllHeroes()
                _heroesDataModel.value = entityToUiMapper.map(localHeroes)
                val remoteHeroes = marvelRepository.getHeroesList()
                val entities = dtoToEntityMapper.map(remoteHeroes)
                entities.forEach { heroDao.insert(it) }
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarvelApiStatus.ERROR
                Log.e("MarvelViewModel", "Error fetching heroes", e)
            }
        }
    }
}