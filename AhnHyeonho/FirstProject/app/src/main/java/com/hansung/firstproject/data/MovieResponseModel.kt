package com.hansung.firstproject.network


// 검색된 결과에 대한 여러 Movie들에 대한 data class
data class MovieResponseModel(
    val total: Int?,
    val start: Int?,
    val items: List<MovieRepoModel>?
)

