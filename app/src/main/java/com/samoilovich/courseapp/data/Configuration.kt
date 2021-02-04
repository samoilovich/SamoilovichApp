package com.samoilovich.courseapp.data

import com.google.gson.annotations.SerializedName

data class Configuration(

	@field:SerializedName("images")
	val images: Images? = null,

	@field:SerializedName("change_keys")
	val changeKeys: List<String?>? = null
)