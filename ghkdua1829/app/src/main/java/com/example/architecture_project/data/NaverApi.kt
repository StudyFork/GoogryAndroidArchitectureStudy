package com.example.architecture_project.data

import com.google.gson.annotations.SerializedName

data class NaverApi(
    @SerializedName("items")
    val item:ArrayList<Movie>
)