package com.samoilovich.courseapp.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samoilovich.courseapp.databinding.ItemStarBinding

class RatingAdapter(private var rating: Int) :
    RecyclerView.Adapter<RatingAdapter.RatingViewHolder>() {

    private val maxRating = 5

    inner class RatingViewHolder(private val binding: ItemStarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            when {
                rating == 0 -> binding.ratingStar.isChecked = false
                position < rating -> binding.ratingStar.isChecked = true
                else -> binding.ratingStar.isChecked = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        return RatingViewHolder(ItemStarBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = maxRating


    fun getRating() = rating
    fun setRating(newRating: Int) {
        rating = when {
            newRating < 0 -> 0
            newRating > maxRating -> maxRating
            else -> newRating
        }
    }
}