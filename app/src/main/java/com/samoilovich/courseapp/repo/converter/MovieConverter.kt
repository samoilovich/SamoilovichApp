package com.samoilovich.courseapp.repo.converter

import com.samoilovich.courseapp.data.Movie
import com.samoilovich.courseapp.repo.model.MoviesPopularResponse

object MovieConverter {

    fun moviesPopularResponseToMovieList(moviesPopularResponse: MoviesPopularResponse): List<Movie> {
        val movies = mutableListOf<Movie>()
        moviesPopularResponse.movies?.let { moviesResponse ->
            for (movieResponse in moviesResponse) {
                movieResponse?.let {
                    val movie = Movie(
                        popularity = movieResponse.popularity,
                        voteCount = movieResponse.voteCount,
                        video = movieResponse.video,
                        posterPath = movieResponse.posterPath,
                        id = movieResponse.id,
                        adult = movieResponse.adult,
                        backdropPath = movieResponse.backdropPath,
                        originalLanguage = movieResponse.originalLanguage,
                        originalTitle = movieResponse.originalTitle,
                        runtime = movieResponse.runtime,
                        genreIds = movieResponse.genreIds,
                        title = movieResponse.title,
                        actorIds = movieResponse.actorIds,
                        voteAverage = movieResponse.voteAverage,
                        overview = movieResponse.overview,
                        releaseDate = movieResponse.releaseDate
                    )
                    movies.add(movie)
                }
            }
        }

        return movies
    }
}