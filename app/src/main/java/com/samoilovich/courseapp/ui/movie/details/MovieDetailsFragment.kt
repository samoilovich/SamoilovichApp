package com.samoilovich.courseapp.ui.movie.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.databinding.FragmentMoviesDetailsBinding
import com.samoilovich.courseapp.ext.addHorizontalDivider
import com.samoilovich.courseapp.models.Movie

class MovieDetailsFragment : Fragment() {

    companion object {
        const val MOVIE_KEY = "MOVIE_KEY"

        fun newInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_KEY, movie)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentMoviesDetailsBinding
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        prepareViews()
    }

    private fun initVariables() {
        movie = arguments?.getParcelable(MOVIE_KEY)
    }

    private fun prepareViews() {
        binding.movieBack.setOnClickListener { activity?.onBackPressed() }
        prepareMovieInfo()
        prepareRating()
        prepareCast()
    }

    private fun prepareMovieInfo() {
        movie?.let { movieInfo ->
            binding.imMoviePoster.setImageResource(movieInfo.poster)
            binding.tvMovieName.text = movieInfo.name
            binding.tvMovieAgeLimit.text = movieInfo.ageLimit
            binding.tvMovieGenres.text = movieInfo.genres
        }
    }

    private fun prepareRating() {
        movie?.let { movieInfo ->
            binding.movieRating.setRatingAndReviews(movieInfo.rating, movieInfo.reviews)
        }
    }

    private fun prepareCast() {
        binding.movieActors.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = CastAdapter(movie?.actors ?: listOf())
            addHorizontalDivider(R.drawable.divider)
        }
    }
}