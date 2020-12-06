package com.samoilovich.courseapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samoilovich.courseapp.databinding.ActivityMainBinding
import com.samoilovich.courseapp.models.Movie
import com.samoilovich.courseapp.ui.movie.details.MovieDetailsFragment
import com.samoilovich.courseapp.ui.movie.list.MoviesLisFragment
import com.samoilovich.courseapp.ui.movie.list.OnMovieClickListener

class MainActivity : AppCompatActivity(), OnMovieClickListener {

    companion object {
        const val MOVIE_DETAILS_TAG = "MOVIE_DETAILS_TAG"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareViews(savedInstanceState)
    }

    private fun prepareViews(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MoviesLisFragment())
                .commit()
        }
    }

    override fun onMovieClick(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(movie))
            .addToBackStack(MOVIE_DETAILS_TAG)
            .commit()
    }
}