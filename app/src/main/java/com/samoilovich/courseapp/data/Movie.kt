package com.samoilovich.courseapp.data

import android.content.Context
import android.os.Parcelable
import androidx.annotation.Keep
import com.samoilovich.courseapp.R
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Movie(
    var popularity: Float,
    var voteCount: Int,
    var video: Boolean,
    var posterPath: String,
    var id: String,
    var adult: Boolean,
    var backdropPath: String,
    var originalLanguage: String,
    var originalTitle: String,
    var runtime: Int,
    var genreIds: List<Int>,
    var title: String,
    var actorIds: List<Int>?,
    var voteAverage: Float,
    var overview: String,
    var releaseDate: String,

    var isFavorite: Boolean = false,
    var genres: MutableList<Genre>? = mutableListOf(),
    var actorList: List<Actor> = listOf()
) : Parcelable {

    fun getAgeLimit(context: Context): String {
        return if (adult) {
            context.getString(R.string.adult_age_limit)
        } else {
            context.getString(R.string.no_age_limit)
        }
    }

    fun getRating(): Int = (voteAverage / 2).toInt()

    fun getGenreNames(): String {
        genres?.let { genres ->
            return buildString {
                if (genres.size > 1) {
                    for (index in 0..genres.size - 2) {
                        append(genres[index].name).append(", ")
                    }
                }
                append(genres.last().name)
            }
        }
        return ""
    }
}