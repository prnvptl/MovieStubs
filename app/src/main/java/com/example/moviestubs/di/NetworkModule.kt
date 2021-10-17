package com.example.moviestubs.di

import com.example.moviestubs.network.MovieApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

private const val API_BASE_URL = "https://interview.zocdoc.com/api/1/FEE/"
private const val AUTH_TOKEN = "3b502b3f-b1ff-4128-bd99-626e74836d9c"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesMovieApiService(
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): MovieApiService {
        val gsonConverter = GsonBuilder().create();
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
            .client(okHttpClient)
            .baseUrl(API_BASE_URL)
            .build()
            .create(MovieApiService::class.java)
    }

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }
}

val authInterceptor = Interceptor { chain ->
    val url = chain.request()
        .url()
        .newBuilder()
        .addQueryParameter("authToken", AUTH_TOKEN)
        .build()
    val request = chain.request()
        .newBuilder()
        .url(url)
        .build()

    return@Interceptor chain.proceed(request)
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient