package com.samoilovich.courseapp.ui.movie.list

import android.content.res.AssetManager.ACCESS_STREAMING
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.data.Movie
import com.samoilovich.courseapp.databinding.FragmentMoviesListBinding
import com.samoilovich.courseapp.ext.addHorizontalDivider
import com.samoilovich.courseapp.ext.addVerticalDivider
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MoviesLisFragment : Fragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private lateinit var binding: FragmentMoviesListBinding
    private val gson = Gson()
    private var moviesAdapter: MoviesAdapter? = null

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
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = moviesAdapter
            addHorizontalDivider(R.drawable.divider_large)
            addVerticalDivider(R.drawable.divider_large)
        }
    }

    private fun initVariables() {
        coroutineScope.launch {
            val movies = getMovies()
            withContext(Main) { moviesAdapter?.submitList(movies) }
        }
    }

    private fun getMovies(): MutableList<Movie> {
        var moviesStr =
            context?.assets?.open("data.json", ACCESS_STREAMING)?.bufferedReader()?.use {
                it.readText()
            }
        moviesStr = moviesStr?.replace("\r\n", "")
        return gson.fromJson(moviesStr, Array<Movie>::class.java).toMutableList()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}