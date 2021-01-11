package com.samoilovich.courseapp.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
class Actor(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("gender") val gender: Int,
    @SerialName("profile_path") val profilePath: String,
)