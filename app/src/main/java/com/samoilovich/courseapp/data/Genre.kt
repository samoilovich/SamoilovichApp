package com.samoilovich.courseapp.data

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Genre(
    @SerialName("id") var id: Int,
    @SerialName("name") var name: String
)