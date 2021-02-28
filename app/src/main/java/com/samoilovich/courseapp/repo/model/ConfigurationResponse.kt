package com.samoilovich.courseapp.repo.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ConfigurationResponse(

	@SerializedName("images")
	val images: ImagesResponse? = null,

	@SerializedName("change_keys")
	val changeKeys: List<String?>? = null
)