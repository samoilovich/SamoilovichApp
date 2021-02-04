package com.samoilovich.courseapp.repo

import com.samoilovich.courseapp.data.Configuration
import com.samoilovich.courseapp.data.MoviesPopular
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object {
        private const val API_KEY = "61c86517edd62bf89b4b6ae25270b82f"
    }

    private var service: TheMovieDbService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(TheMovieDbService::class.java)
    }

    suspend fun getMoviesPopular(): MoviesPopular {
        return service.getMoviePopular(apiKey = API_KEY, language = "en-US", page = 1)
    }

    suspend fun getConfiguration(): Configuration {
        return service.getConfiguration(apiKey = API_KEY)
    }
}