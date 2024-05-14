package com.example.marvelapp.ui.screens.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.local.dao.HeroDao
import com.example.marvelapp.data.mapper.HeroDtoToEntityMapper
import com.example.marvelapp.data.mapper.HeroEntityToUiMapper
import com.example.marvelapp.data.model.ui.UiResultsModel
import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.ui.components.MarvelApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository,
    private val heroDao: HeroDao,
    private val dtoToEntityMapper: HeroDtoToEntityMapper,
    private val entityToUiMapper: HeroEntityToUiMapper
    ) : ViewModel() {

    private val _status = MutableLiveData<MarvelApiStatus>()
    val status: LiveData<MarvelApiStatus> = _status

    private val _heroDataModel = MutableLiveData<UiResultsModel>()
    val heroDataModel: LiveData<UiResultsModel> = _heroDataModel

    fun getHeroById(heroId: Int) {
        viewModelScope.launch {
            try {
                _status.value = MarvelApiStatus.LOADING
                val localHero = heroDao.heroById(heroId)
                if (localHero != null) {
                    val uiHeroData = entityToUiMapper.map(localHero)
                    _heroDataModel.value = uiHeroData
                } else {
                    val remoteHero = marvelRepository.getHeroById(heroId)
                    val entity = dtoToEntityMapper.map(remoteHero)
                    entity.forEach { heroDao.insert(it) }
                }
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarvelApiStatus.ERROR
                Log.e("DetailViewModel", "Error fetching hero data", e)
            }
        }
    }
}