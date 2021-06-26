package com.faraji.mvibase.example.data.model

import com.google.gson.annotations.SerializedName

enum class SizeData {
    @SerializedName("full")
    FULL,

    @SerializedName("med")
    MED,

    @SerializedName("small")
    SMALL,

    @SerializedName("thumb")
    THUMB,
}