package com.example.androidarchitecture.data.response

import com.google.gson.annotations.SerializedName

data class NaverQueryResponse<T>(
    @SerializedName("items")
    val items: List<T>
)