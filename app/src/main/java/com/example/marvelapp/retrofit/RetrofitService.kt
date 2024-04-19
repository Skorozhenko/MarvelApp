package com.example.marvelapp.retrofit

import com.example.marvelapp.retrofit.api.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitService {

    @Provides
    fun provideOkHttpClient(constants: Constants): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLogging())
            .addInterceptor(constants)
            .build()
    }

    private fun createLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }
}