package com.samoilovich.courseapp.repo

import com.samoilovich.courseapp.repo.model.ConfigurationResponse
import com.samoilovich.courseapp.repo.model.GenresResponse
import com.samoilovich.courseapp.repo.model.MoviesPopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesPopularResponse

    @GET("configuration")
    suspend fun getConfiguration(@Query("api_key") apiKey: String): ConfigurationResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): GenresResponse
}