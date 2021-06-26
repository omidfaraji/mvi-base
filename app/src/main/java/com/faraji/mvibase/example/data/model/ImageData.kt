package com.faraji.mvibase.example.data.model

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String,
)
