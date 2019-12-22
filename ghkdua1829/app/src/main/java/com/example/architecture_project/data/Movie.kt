package com.example.architecture_project.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("")
    val image: String,
    @SerializedName("")
    val title: String,
    @SerializedName("")
    val star: Int,
    @SerializedName("")
    val director: String,
    @SerializedName("")
    val desc: String
)