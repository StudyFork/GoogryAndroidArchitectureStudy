package com.practice.achitecture.myproject.model

data class ResultOfSearchingModel(
    val display: Int,
    val items: ArrayList<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)