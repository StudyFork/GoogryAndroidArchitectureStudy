package com.example.androidarchitecturestudy.data.model

import com.google.gson.annotations.SerializedName

data class QueryHistory(
    @SerializedName("queryHistory")
    val queryHistory: String?
)