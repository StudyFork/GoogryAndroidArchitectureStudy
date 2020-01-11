package com.example.architecture_project.data.model

import com.google.gson.annotations.SerializedName

data class NaverApi(
    val total : Int,
    @SerializedName("items")
    val item: List<Movie>
)