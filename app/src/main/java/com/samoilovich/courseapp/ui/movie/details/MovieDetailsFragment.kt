package com.samoilovich.courseapp.ui.movie.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.data.Movie
import com.samoilovich.courseapp.databinding.FragmentMoviesDetailsBinding
import com.samoilovich.courseapp.ext.addHorizontalDivider

class MovieDetailsFragment : Fragment() {

    companion object {
        private const val COLUMNS_AMOUNT = 4
        const val MOVIE_KEY = "MOVIE_KEY"

        fun newInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = bundleOf(Pair(MOVIE_KEY, movie))
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentMoviesDetailsBinding
    private val viewModel: MoviesDetailsViewModel by viewModels()
    private var castAdapter: CastAdapter? = null
    private val movie: Movie? by lazy { arguments?.getParcelable(MOVIE_KEY) }

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
        viewModel.actorsLiveData.observe(viewLifecycleOwner) { actors ->
            movie?.actorList = actors
            castAdapter?.updateActors(actors)
            binding.movieCast.isVisible = actors.isNotEmpty()
        }
        viewModel.getMovieActors(context?.assets, movie?.actorIds)
    }

    private fun prepareViews() {
        binding.movieBack.setOnClickListener { activity?.onBackPressed() }
        prepareMovieInfo()
        prepareRating()
        prepareCast()
    }

    private fun prepareMovieInfo() {
        movie?.let { movieInfo ->
            Glide.with(binding.imMoviePoster)
                .load(movieInfo.backdropPath)
                .into(binding.imMoviePoster)
            binding.tvMovieName.text = movieInfo.title
            binding.tvMovieAgeLimit.text = movieInfo.getAgeLimit(requireContext())
            binding.tvMovieGenres.text = movieInfo.getGenreNames()
            binding.movieStorylineText.text = movieInfo.overview
        }
    }

    private fun prepareRating() {
        movie?.let { movieInfo ->
            binding.movieRating.setRatingAndReviews(movieInfo.getRating(), movieInfo.voteCount)
        }
    }

    private fun prepareCast() {
        castAdapter = CastAdapter(movie?.actorList ?: listOf())
        binding.movieActors.apply {
            layoutManager = GridLayoutManager(context, COLUMNS_AMOUNT)
            adapter = castAdapter
            addHorizontalDivider(R.drawable.divider)
        }
    }
}