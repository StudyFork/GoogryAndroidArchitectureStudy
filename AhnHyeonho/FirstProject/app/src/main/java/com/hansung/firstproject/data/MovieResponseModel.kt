package com.hansung.firstproject.data


// 검색된 결과를 받아올 data class
data class MovieResponseModel(
    val display: Int,
    val items: ArrayList<MovieModel>
)

