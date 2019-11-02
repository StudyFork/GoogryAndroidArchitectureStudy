package com.practice.achitecture.myproject.model

data class ResultOfSearchingModel(
    val display: Int,
    val searchedItems: List<SearchedItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)