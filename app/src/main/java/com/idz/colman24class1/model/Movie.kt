package com.idz.colman24class1.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("poster_path")
    val posterPath: String,

    val title: String,
)