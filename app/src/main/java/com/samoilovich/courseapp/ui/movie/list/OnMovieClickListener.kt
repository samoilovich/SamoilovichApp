package com.samoilovich.courseapp.ui.movie.list

import com.samoilovich.courseapp.data.Movie

interface OnMovieClickListener {

    fun onMovieClick(movie: Movie)
}