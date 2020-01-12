package com.example.study.data.model

import com.google.gson.annotations.SerializedName

data class NaverSearch(
    @SerializedName("items")
    val items: List<Movie>
)