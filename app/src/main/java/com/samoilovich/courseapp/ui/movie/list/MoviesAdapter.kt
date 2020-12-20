package com.samoilovich.courseapp.ui.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.databinding.ItemMovieBinding
import com.samoilovich.courseapp.data.Movie

class MoviesAdapter(
    var movies: List<Movie>,
    var onMovieClickListener: OnMovieClickListener? = null
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvMovieName.text = movie.name
            binding.imMoviePoster.setImageResource(movie.poster)
            binding.tvMovieAgeLimit.text = movie.ageLimit
            binding.tvMovieGenres.text = movie.genres
            binding.rvMovieRating.setRatingAndReviews(movie.rating, movie.reviews)
            binding.tvMovieDuration.text =
                binding.root.context.getString(R.string.duration_placeholder).format(movie.duration)
            if (movie.isFavorite) {
                binding.ivMovieLike.setImageResource(R.drawable.ic_liked)
            } else {
                binding.ivMovieLike.setImageResource(R.drawable.ic_like)
            }
            binding.root.setOnClickListener {
                onMovieClickListener?.onMovieClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}