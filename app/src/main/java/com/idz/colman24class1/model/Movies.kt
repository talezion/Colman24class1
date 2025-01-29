package com.idz.colman24class1.model

import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Int,
    val results: List<Movie>,

    @SerializedName("total_pages")
    val totalPages: String,

    @SerializedName("total_results")
    val totalResults: String,
)
