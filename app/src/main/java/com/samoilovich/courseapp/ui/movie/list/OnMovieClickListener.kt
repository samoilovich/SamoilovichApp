package com.samoilovich.courseapp.ui.movie.list

import com.samoilovich.courseapp.models.Movie

interface OnMovieClickListener {

    fun onMovieClick(movie: Movie)
}