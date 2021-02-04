package com.samoilovich.courseapp.data

import com.google.gson.annotations.SerializedName

data class MoviesPopular(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val movies: List<Movie?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)