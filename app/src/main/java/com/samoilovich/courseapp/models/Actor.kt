package com.samoilovich.courseapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Actor(
    val name: String,
    val avatar: Int
): Parcelable