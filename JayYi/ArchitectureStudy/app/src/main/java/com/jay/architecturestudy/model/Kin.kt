package com.jay.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class Kin(
    val description: String,
    val link: String,
    val title: String
)

data class ResponseKin(
    val display: Int,
    @SerializedName("items")
    val kins: List<Kin>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)