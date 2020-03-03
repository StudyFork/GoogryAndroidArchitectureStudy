package com.kmj.study.dto

import java.util.*

data class MovieResponseDto(
    val display: Int,
    val items: ArrayList<MovieDto>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)