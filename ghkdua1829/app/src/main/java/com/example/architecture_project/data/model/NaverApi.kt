package com.example.architecture_project.data.model

import com.google.gson.annotations.SerializedName

data class NaverApi(
    @SerializedName("items")
    val item: ArrayList<Movie>
)