package com.samoilovich.courseapp.ui.movie.list

import android.content.res.AssetManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.samoilovich.courseapp.data.Genre
import com.samoilovich.courseapp.data.Movie
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    val moviesLiveData: MutableLiveData<MutableList<Movie>> by lazy { MutableLiveData<MutableList<Movie>>() }
    private val gson = Gson()

    fun getMovies(assets: AssetManager?) {
        val reader = assets?.open("data.json", AssetManager.ACCESS_STREAMING)?.bufferedReader()
        viewModelScope.launch {
            var moviesStr = reader?.use { it.readText() }
            moviesStr = moviesStr?.replace("\r\n", "")
            val movies = gson.fromJson(moviesStr, Array<Movie>::class.java).toMutableList()
            val genres = getGenres(assets)
            for (movie in movies) {
                val movieGenres = mutableListOf<Genre>()
                for (genreId in movie.genreIds) {
                    val foundGenres = genres.filter { genre -> genre.id == genreId }
                    movieGenres.addAll(foundGenres)
                }
                movie.genres = movieGenres
            }
            moviesLiveData.value = movies
        }
    }

    private fun getGenres(assets: AssetManager?): MutableList<Genre> {
        val genresStr =
            assets?.open("genres.json", AssetManager.ACCESS_STREAMING)?.bufferedReader()?.use {
                it.readText()
            }
        return gson.fromJson(genresStr, Array<Genre>::class.java).toMutableList()
    }
}