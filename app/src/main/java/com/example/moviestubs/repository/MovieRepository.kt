package com.example.moviestubs.repository

import com.example.moviestubs.model.Movie

interface   MovieRepository {
    suspend fun getMovies() : List<Movie>
    suspend fun getMoviesByRank(startRank: Int, numMovies: Int) : List<Movie>
    suspend fun getMovieDetails(ids: List<Int>) : List<Movie>
}