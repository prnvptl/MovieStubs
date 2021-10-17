package com.example.moviestubs.repository

import com.example.moviestubs.model.Movie
import com.example.moviestubs.network.MovieApiService

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
) : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return movieApiService.getMovies()
    }

    override suspend fun getMoviesByRank(startRank: Int, numMovies: Int): List<Movie> {
        return movieApiService.getMoviesByRank(startIndex = startRank, numMovies = numMovies)
    }

    override suspend fun getMovieDetails(ids: List<Int>): List<Movie> {
        return movieApiService.getMovieDetails(ids = ids)
    }
}