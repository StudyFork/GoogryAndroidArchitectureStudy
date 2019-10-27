package com.practice.achitecture.myproject.model

data class ResultOfSearchingModel(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)