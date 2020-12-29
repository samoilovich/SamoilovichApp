package com.samoilovich.courseapp.data

import android.content.Context
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.samoilovich.courseapp.R
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Movie(
    @SerializedName("popularity") var popularity: Float,
    @SerializedName("vote_count") var voteCount: Int,
    @SerializedName("video") var video: Boolean,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("id") var id: String,
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("runtime") var runtime: Int,
    @SerializedName("genre_ids") var genreIds: List<Int>,
    @SerializedName("title") var title: String,
    @SerializedName("actors") var actorIds: List<Int>,
    @SerializedName("vote_average") var voteAverage: Float,
    @SerializedName("overview") var overview: String,
    @SerializedName("release_date") var releaseDate: String,

    var isFavorite: Boolean = false
) : Parcelable {

    fun getAgeLimit(context: Context): String {
        return if (adult) {
            context.getString(R.string.adult_age_limit)
        } else {
            context.getString(R.string.no_age_limit)
        }
    }

    fun getRating(): Int = (voteAverage / 2).toInt()
}