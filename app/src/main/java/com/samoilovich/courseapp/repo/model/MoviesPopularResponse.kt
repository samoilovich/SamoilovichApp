package com.samoilovich.courseapp.repo.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MoviesPopularResponse(

	@SerializedName("page")
	val page: Int? = null,

	@SerializedName("total_pages")
	val totalPages: Int? = null,

	@SerializedName("results")
	val movies: List<MovieResponse?>? = null,

	@SerializedName("total_results")
	val totalResults: Int? = null
)