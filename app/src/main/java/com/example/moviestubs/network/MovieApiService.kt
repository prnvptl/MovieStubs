package com.example.moviestubs.network

import com.example.moviestubs.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

private const val AUTH_TOKEN = "3b502b3f-b1ff-4128-bd99-626e74836d9c"

interface MovieApiService {
    @GET("AllMovies")
    suspend fun getMovies(@Query("authToken") token: String = AUTH_TOKEN) : List<Movie>

    @GET("MoviesByRank")
    suspend fun getMoviesByRank(
        @Query("authToken") token: String = AUTH_TOKEN,
        @Query("startRankIndex") startIndex: Int,
        @Query("numMovies") numMovies: Int
    ) : List<Movie>

    @GET("MovieDetails")
    suspend fun getMovieDetails(
        @Query("authToken") token: String = AUTH_TOKEN,
        @Query("movieIds") ids: List<Int>
    ) : List<Movie>
}

