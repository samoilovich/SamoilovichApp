package com.samoilovich.courseapp.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samoilovich.courseapp.data.Genre
import com.samoilovich.courseapp.data.Movie
import com.samoilovich.courseapp.repo.ApiService
import com.samoilovich.courseapp.repo.converter.GenreConverter
import com.samoilovich.courseapp.repo.converter.MovieConverter
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val service = ApiService()
    private val _moviesLiveData: MutableLiveData<List<Movie?>> by lazy { MutableLiveData<List<Movie?>>() }
    val moviesLiveData: LiveData<List<Movie?>> = _moviesLiveData

    fun getMovies() {
        viewModelScope.launch {
            val configuration = service.getConfiguration()
            val response = service.getMoviesPopular()
            val posterSize = configuration.images?.posterSizes?.get(2)
            val moviePosterUrl = "${configuration.images?.secureBaseUrl}/$posterSize"
            val genres = getGenres()
            val movies = MovieConverter.moviesPopularResponseToMovieList(response)
            if (movies.isNotEmpty()) {
                for (movie in movies) {
                    movie.posterPath = "$moviePosterUrl/${movie.posterPath}"
                    for (genreId in movie.genreIds) {
                        val genre = genres[genreId]
                        genre?.let {
                            movie.genres?.add(genre)
                        }
                    }
                }
            }
            _moviesLiveData.value = movies
        }
    }

    private suspend fun getGenres(): HashMap<Int, Genre> {
        val response = service.getGenres()
        return GenreConverter.genresResponseToGenreList(response)
    }
}