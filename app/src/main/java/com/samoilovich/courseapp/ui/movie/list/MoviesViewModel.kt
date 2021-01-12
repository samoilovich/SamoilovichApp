package com.samoilovich.courseapp.ui.movie.list

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.samoilovich.courseapp.data.Genre
import com.samoilovich.courseapp.data.Movie
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val _moviesLiveData: MutableLiveData<List<Movie>> by lazy { MutableLiveData<List<Movie>>() }
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData
    private val gson = Gson()

    fun getMovies(assets: AssetManager?) {
        val reader = assets?.open("data.json", AssetManager.ACCESS_STREAMING)?.bufferedReader()
        viewModelScope.launch {
            var moviesStr = reader?.use { it.readText() }
            moviesStr = moviesStr?.replace("\r\n", "")
            val movies = gson.fromJson(moviesStr, Array<Movie>::class.java).toMutableList()
            val genresMap = getGenres(assets)
            for (movie in movies) {
                val movieGenres = mutableListOf<Genre>()
                for (genreId in movie.genreIds) {
                    genresMap[genreId]?.let { genre -> movieGenres.add(genre) }
                }
                movie.genres = movieGenres
            }
            _moviesLiveData.value = movies
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