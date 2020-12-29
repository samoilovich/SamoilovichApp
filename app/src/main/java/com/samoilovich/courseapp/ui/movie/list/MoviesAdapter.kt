package com.samoilovich.courseapp.ui.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.data.Movie
import com.samoilovich.courseapp.databinding.ItemMovieBinding

class MoviesAdapter(
    var onMovieClickListener: OnMovieClickListener? = null
) : ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvMovieName.text = movie.title
            Glide.with(binding.imMoviePoster)
                .load(movie.posterPath)
                .centerCrop()
                .into(binding.imMoviePoster)
            binding.tvMovieAgeLimit.text = movie.getAgeLimit(binding.tvMovieAgeLimit.context)
            // todo add logic to get genres
//            binding.tvMovieGenres.text = movie.genres
            binding.rvMovieRating.setRatingAndReviews(movie.getRating(), movie.voteCount)
            binding.tvMovieDuration.text =
                binding.root.context.getString(R.string.duration_placeholder).format(movie.runtime)
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
        holder.bind(getItem(position))
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}