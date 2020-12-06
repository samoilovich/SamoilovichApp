package com.samoilovich.courseapp.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.databinding.FragmentMoviesListBinding
import com.samoilovich.courseapp.ext.addHorizontalDivider
import com.samoilovich.courseapp.ext.addVerticalDivider
import com.samoilovich.courseapp.models.Actor
import com.samoilovich.courseapp.models.Movie

class MoviesLisFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViews()
    }

    private fun prepareViews() {
        val activityRef = activity
        val onMovieClickListener: OnMovieClickListener? =
            if (activityRef is OnMovieClickListener) activityRef else null
        binding.rvMoviesList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = MoviesAdapter(getMovies(), onMovieClickListener)
            addHorizontalDivider(R.drawable.divider_large)
            addVerticalDivider(R.drawable.divider_large)
            setHasFixedSize(true)
        }
    }

    private fun getMovies(): List<Movie> {
        val movies = mutableListOf<Movie>()

        val actors = mutableListOf<Actor>()
        actors.add(Actor("Robert Downey Jr.", R.drawable.avatar_robert))
        actors.add(Actor("Chris Evans", R.drawable.avatar_chris))
        actors.add(Actor("Mark Ruffalo", R.drawable.avatar_mark))
        actors.add(Actor("Chris Hemsworth", R.drawable.avatar_chris_thor))

        movies.add(
            Movie(
                "Avengers: End Game",
                R.drawable.poster_avengers,
                "13+",
                "Action, Adventure, Drama",
                4,
                125,
                137,
                false,
                actors
            )
        )
        movies.add(
            Movie(
                "Tenet",
                R.drawable.poster_tenet,
                "16+",
                "Action, Sci-Fi, Thriller ",
                5,
                98,
                97,
                true,
                actors
            )
        )
        movies.add(
            Movie(
                "Black Widow",
                R.drawable.poster_black_widow,
                "13+",
                "Action, Adventure, Sci-Fi",
                4,
                38,
                102,
                false,
                actors
            )
        )
        movies.add(
            Movie(
                "Wonder Woman 1984",
                R.drawable.poster_wonder_woman,
                "13+",
                "Action, Adventure, Fantasy",
                4,
                74,
                120,
                false,
                actors
            )
        )
        return movies
    }
}