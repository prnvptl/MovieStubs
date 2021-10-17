package com.example.moviestubs.network

import com.example.moviestubs.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("AllMovies")
    suspend fun getMovies() : List<Movie>

    @GET("MoviesByRank")
    suspend fun getMoviesByRank(
        @Query("startRankIndex") startIndex: Int,
        @Query("numMovies") numMovies: Int
    ) : List<Movie>

    @GET("MovieDetails")
    suspend fun getMovieDetails(
        @Query("movieIds") ids: List<Int>
    ) : List<Movie>
}

