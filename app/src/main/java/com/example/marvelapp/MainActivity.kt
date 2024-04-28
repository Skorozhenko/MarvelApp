package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.marvelapp.navigation.Navigation
import com.example.marvelapp.ui.screens.detail.DetailViewModel
import com.example.marvelapp.data.repository.MarvelRepository
import com.example.marvelapp.ui.screens.marvel.MarvelViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val marvelViewModel: MarvelViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Navigation(marvelViewModel, detailViewModel)
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMarvelViewModel(marvelRepository: MarvelRepository): MarvelViewModel {
        return MarvelViewModel(marvelRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailViewModel(marvelRepository: MarvelRepository): DetailViewModel {
        return DetailViewModel(marvelRepository)
    }
}
