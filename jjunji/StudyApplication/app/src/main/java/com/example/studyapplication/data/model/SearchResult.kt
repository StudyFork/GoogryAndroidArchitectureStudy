package com.example.studyapplication.data.model

import com.google.gson.annotations.SerializedName

class SearchResult<T> {
    @SerializedName("items")
    val arrItem = arrayListOf<T>()
}