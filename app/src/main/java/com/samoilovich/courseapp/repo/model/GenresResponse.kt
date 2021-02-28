package com.samoilovich.courseapp.repo.model

import androidx.annotation.Keep

@Keep
data class GenresResponse(
	val genres: List<GenreItemsResponse?>? = null
)
