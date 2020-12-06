package com.samoilovich.courseapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
    var name: String,
    var poster: Int,
    var ageLimit: String,
    var genres: String,
    var rating: Int,
    var reviews: Int,
    var duration: Int,
    var isFavorite: Boolean,
    var actors: List<Actor>
) : Parcelable