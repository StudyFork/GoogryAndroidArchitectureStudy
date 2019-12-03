package com.example.androidarchitecture.models

import com.google.gson.annotations.SerializedName

data class KinData(

    @SerializedName("items")
    val kins: List<ResponseKin>
)

data class ResponseKin(
    val description: String,
    val link: String,
    val title: String
)
