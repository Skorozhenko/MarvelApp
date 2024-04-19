package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.marvelapp.navigation.Navigation
import com.example.marvelapp.retrofit.api.MarvelApi
import com.example.marvelapp.retrofit.repository.MarvelRepository
import com.example.marvelapp.retrofit.repository.MarvelRepositoryImpl
import com.example.marvelapp.retrofit.MarvelViewModel
import com.example.marvelapp.retrofit.RetrofitService
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.internal.GeneratedComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), GeneratedComponent {

//    @Inject
//    lateinit var marvelRepository: MarvelRepository

    @Inject
    lateinit var marvelViewModel: MarvelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appComponent = DaggerAppComponent.create()
        appComponent.inject(this)
        //appComponent.inject(marvelRepository)
        appComponent.inject(marvelViewModel)

        setContent {
            Navigation()
        }
    }
}

@Component(modules = [AppModule::class, RetrofitService::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(viewModel: MarvelViewModel)
    fun inject(marvelRepository: MarvelRepository)
    //fun marvelViewModel(): MarvelViewModel
}

@Module
class AppModule {
    @Provides
    fun provideMarvelRepository(api: MarvelApi): MarvelRepository {
        return MarvelRepositoryImpl(api)
    }

    @Provides
    fun provideMarvelViewModel(repository: MarvelRepository): MarvelViewModel {
        return MarvelViewModel(repository)
    }
}
