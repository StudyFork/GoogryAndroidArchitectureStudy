package com.example.androidarchitecture.models

import com.google.gson.annotations.SerializedName

data class NaverQueryResponse<T>(
    @SerializedName("items")
    val items: List<T>
)