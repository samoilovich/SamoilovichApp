package com.samoilovich.courseapp.data

import android.content.Context
import androidx.annotation.Keep
import com.samoilovich.courseapp.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Movie(
    @SerialName("popularity") var popularity: Float,
    @SerialName("vote_count") var voteCount: Int,
    @SerialName("video") var video: Boolean,
    @SerialName("poster_path") var posterPath: String,
    @SerialName("id") var id: String,
    @SerialName("adult") var adult: Boolean,
    @SerialName("backdrop_path") var backdropPath: String,
    @SerialName("original_language") var originalLanguage: String,
    @SerialName("original_title") var originalTitle: String,
    @SerialName("runtime") var runtime: Int,
    @SerialName("genre_ids") var genreIds: List<Int>,
    @SerialName("title") var title: String,
    @SerialName("actors") var actorIds: List<Int>,
    @SerialName("vote_average") var voteAverage: Float,
    @SerialName("overview") var overview: String,
    @SerialName("release_date") var releaseDate: String,

    var isFavorite: Boolean = false,
    var genres: List<Genre> = listOf(),
    var actorList: List<Actor> = listOf()
) {

    fun getAgeLimit(context: Context): String {
        return if (adult) {
            context.getString(R.string.adult_age_limit)
        } else {
            context.getString(R.string.no_age_limit)
        }
    }

    fun getRating(): Int = (voteAverage / 2).toInt()

    fun getGenreNames(): String {
        val nameBuilder = StringBuilder()
        if (genres.size > 1) {
            for (index in 0..genres.size - 2) {
                nameBuilder.append(genres[index].name).append(", ")
            }
        }
        nameBuilder.append(genres.last().name)
        return nameBuilder.toString()
    }
}