package com.samoilovich.courseapp.repo

import com.samoilovich.courseapp.data.Configuration
import com.samoilovich.courseapp.data.MoviesPopular
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesPopular

    @GET("configuration")
    suspend fun getConfiguration(@Query("api_key") apiKey: String): Configuration
}