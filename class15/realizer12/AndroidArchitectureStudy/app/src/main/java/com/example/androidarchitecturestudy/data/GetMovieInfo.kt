package com.example.androidarchitecturestudy.data

import com.google.gson.annotations.SerializedName

class GetMovieInfo {

    //검색 쿼리에 따른 네이버 영화 리스트
    data class MovieList(
        @SerializedName("items")
        val movieList: List<MovieData>
    )

    //각 영화별 구성된 데이터
    data class MovieData(
        @SerializedName("image")
        val image: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("userRating")
        val userRating: String
    )
}