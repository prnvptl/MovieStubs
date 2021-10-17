package com.example.moviestubs.di

import com.example.moviestubs.network.MovieApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val API_BASE_URL = "https://interview.zocdoc.com/api/1/FEE/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesMovieApiService(): MovieApiService {
        val gsonConverter = GsonBuilder().create();
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
            .baseUrl(API_BASE_URL)
            .build()
            .create(MovieApiService::class.java)
    }
}