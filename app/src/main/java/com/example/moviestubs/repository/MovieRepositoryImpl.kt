package com.example.moviestubs.repository

import com.example.moviestubs.model.Movie
import com.example.moviestubs.network.MovieApiService

enum class MovieFilter(val value: String) { SHOW_ALL("all"), TOP_10("top10") }

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
) : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return movieApiService.getMovies()
    }

    override suspend fun getMoviesByRank(startRank: Int, numMovies: Int): List<Movie> {
        return movieApiService.getMoviesByRank(startRank, numMovies)
    }

    override suspend fun getMovieDetails(ids: List<Int>): List<Movie> {
        return movieApiService.getMovieDetails( ids)
    }
}