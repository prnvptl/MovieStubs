package com.example.moviestubs.network

import com.example.moviestubs.model.Movie
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://interview.zocdoc.com/api/1/FEE/"
private const val AUTH_TOKEN = "3b502b3f-b1ff-4128-bd99-626e74836d9c"

private val GsonConverter = GsonBuilder().create();

/**
 * Use the Retrofit builder to build a retrofit object using a GsonConverterFactory
 * with our GsonConverter object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(GsonConverter))
    .baseUrl(BASE_URL)
    .build()


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

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MovieApi {
    val retrofitService : MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}
