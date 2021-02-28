package com.samoilovich.courseapp.repo

import com.samoilovich.courseapp.repo.model.ConfigurationResponse
import com.samoilovich.courseapp.repo.model.GenresResponse
import com.samoilovich.courseapp.repo.model.MoviesPopularResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object {
        private const val API_KEY = "61c86517edd62bf89b4b6ae25270b82f"
        private const val LANGUAGE = "en-US"
    }

    private var service: TheMovieDbService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(TheMovieDbService::class.java)
    }

    suspend fun getMoviesPopular(): MoviesPopularResponse {
        return service.getMoviePopular(apiKey = API_KEY, language = LANGUAGE, page = 1)
    }

    suspend fun getConfiguration(): ConfigurationResponse {
        return service.getConfiguration(apiKey = API_KEY)
    }

    suspend fun getGenres(): GenresResponse {
        return service.getGenres(apiKey = API_KEY, language = LANGUAGE)
    }
}