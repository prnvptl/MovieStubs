package com.example.moviestubs.di

import com.example.moviestubs.network.MovieApiService
import com.example.moviestubs.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesMovieRepository(
        movieApiService: MovieApiService
    ): MovieRepositoryImpl {
        return MovieRepositoryImpl(movieApiService)
    }
}