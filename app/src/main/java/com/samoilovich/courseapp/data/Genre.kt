package com.samoilovich.courseapp.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Genre(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String
) : Parcelable