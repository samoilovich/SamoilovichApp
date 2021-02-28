package com.samoilovich.courseapp.ui.movie.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.databinding.FragmentMoviesListBinding
import com.samoilovich.courseapp.ext.addHorizontalDivider
import com.samoilovich.courseapp.ext.addVerticalDivider

class MoviesLisFragment : Fragment() {

    companion object {
        private const val COLUMNS_AMOUNT = 2
    }

    private lateinit var binding: FragmentMoviesListBinding
    private val viewModel: MoviesViewModel by viewModels()
    private var moviesAdapter: MoviesAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getMovies()
    }

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
        initVariables()
    }

    private fun prepareViews() {
        val activityRef = activity
        val onMovieClickListener: OnMovieClickListener? =
            if (activityRef is OnMovieClickListener) activityRef else null
        moviesAdapter = MoviesAdapter(onMovieClickListener)
        binding.rvMoviesList.apply {
            layoutManager = GridLayoutManager(requireContext(), COLUMNS_AMOUNT)
            adapter = moviesAdapter
            addHorizontalDivider(R.drawable.divider_large)
            addVerticalDivider(R.drawable.divider_large)
        }
    }

    private fun initVariables() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { movies ->
            moviesAdapter?.submitList(movies)
        }
    }
}