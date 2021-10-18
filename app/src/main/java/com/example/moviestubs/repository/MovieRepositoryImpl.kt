package com.example.moviestubs.repository

import com.example.moviestubs.model.Movie
import com.example.moviestubs.network.MovieApiService

enum class MovieFilter(val value: String) { SHOW_ALL("all"), TOP_10("top10") }

/**
 * Repository that returns movie data using the [MovieApiService]
 * Although it seems just like a wrapper, this could include other data
 * sources like other data access objects (DAO) in addition to the
 * MovieApiService which retrieves data from the network
 * Reference: https://developer.android.com/jetpack/guide#overview
 */
class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
) : MovieRepository {
    /**
     * Gets all movies for the year
     */
    override suspend fun getMovies(): List<Movie> {
        return movieApiService.getMovies()
    }

    /**
     * [startRank] - the rank the movies should start by
     * [numMovies] - number of movies to be retrieved
     */
    override suspend fun getMoviesByRank(startRank: Int, numMovies: Int): List<Movie> {
        return movieApiService.getMoviesByRank(startRank, numMovies)
    }

    /**
     * [ids]: gets details of movie(s) given the movie id(s)
     */
    override suspend fun getMovieDetails(ids: List<Int>): List<Movie> {
        return movieApiService.getMovieDetails( ids)
    }
}