package com.example.archstudy

import com.example.archstudy.data.source.local.MovieData

data class MovieDataResponse(
    val display: Int,
    val items: List<MovieData>,
    val start: Int,
    val total: Int
)