package com.samoilovich.courseapp.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import com.samoilovich.courseapp.R
import com.samoilovich.courseapp.databinding.ViewRatingBinding

class RatingView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    companion object {
        const val REVIEWS_TEXT_SIZE_DEFAULT_VALUE = 0
        const val MAX_RATING = 5
    }

    private val binding: ViewRatingBinding = ViewRatingBinding.inflate(LayoutInflater.from(context))
    private var ratingViews: MutableList<AppCompatImageView?> = mutableListOf()
    private var reviewsTextSize: Int = REVIEWS_TEXT_SIZE_DEFAULT_VALUE

    init {
        addView(binding.root)
        applyAttrs(attrs)
        prepareViews()
    }

    private fun applyAttrs(attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.RatingView)
        reviewsTextSize = attributes.getDimensionPixelSize(
            R.styleable.RatingView_reviewTextSize,
            REVIEWS_TEXT_SIZE_DEFAULT_VALUE
        )
        attributes.recycle()
    }

    private fun prepareViews() {
        ratingViews.add(binding.ivRating1)
        ratingViews.add(binding.ivRating2)
        ratingViews.add(binding.ivRating3)
        ratingViews.add(binding.ivRating4)
        ratingViews.add(binding.ivRating5)
        prepareReviewsText()
    }

    private fun prepareReviewsText() {
        if (reviewsTextSize != REVIEWS_TEXT_SIZE_DEFAULT_VALUE) {
            binding.tvRatingReviews.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                reviewsTextSize.toFloat()
            )
        }
    }

    fun setRatingAndReviews(rating: Int, reviews: Int) {
        val correctedRating = when {
            rating < 0 -> 0
            rating > MAX_RATING -> MAX_RATING
            else -> rating
        }
        for ((position, view) in ratingViews.withIndex()) {
            if (position < correctedRating) {
                view?.setImageResource(R.drawable.ic_star_checked)
            } else {
                view?.setImageResource(R.drawable.ic_star_default)
            }
        }
        binding.tvRatingReviews.text = if (rating > 1) {
            context.getString(R.string.reviews_placeholder).format(reviews)
        } else {
            context.getString(R.string.reviews_placeholder).format(reviews)
        }
    }
}