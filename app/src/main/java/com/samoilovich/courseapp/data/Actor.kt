package com.samoilovich.courseapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Actor(
    val id: String,
    val name: String,
    val picture: String
): Parcelable