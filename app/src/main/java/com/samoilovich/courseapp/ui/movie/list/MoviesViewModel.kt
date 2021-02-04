package com.samoilovich.courseapp.ui.movie.list

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.samoilovich.courseapp.data.Genre
import com.samoilovich.courseapp.data.Movie
import com.samoilovich.courseapp.repo.ApiService
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val service = ApiService()
    private val _moviesLiveData: MutableLiveData<List<Movie?>> by lazy { MutableLiveData<List<Movie?>>() }
    val moviesLiveData: LiveData<List<Movie?>> = _moviesLiveData
    private val gson = Gson()

    fun getMovies(assets: AssetManager?) {
        viewModelScope.launch {
            val configuration = service.getConfiguration()
            val response = service.getMoviesPopular()
            response.movies?.let { movies ->
                val posterSize = configuration.images?.posterSizes?.get(2)
                val moviePosterUrl = "${configuration.images?.secureBaseUrl}/$posterSize"
                if (movies.isNotEmpty()) {
                    for (movie in movies) {
                        movie?.posterPath = "$moviePosterUrl/${movie?.posterPath}"
                    }
                }
                _moviesLiveData.value = response.movies
            }
        }
    }

    private fun getGenres(assets: AssetManager?): Map<Int, Genre> {
        val genresStr =
            assets?.open("genres.json", AssetManager.ACCESS_STREAMING)?.bufferedReader()?.use {
                it.readText()
            }
        val genres = gson.fromJson(genresStr, Array<Genre>::class.java).toMutableList()
        val genresMap = hashMapOf<Int, Genre>()
        for (genre in genres) {
            genresMap[genre.id] = genre
        }
        return genresMap
    }
}