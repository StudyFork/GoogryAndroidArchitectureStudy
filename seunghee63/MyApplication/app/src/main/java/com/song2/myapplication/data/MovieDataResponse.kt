package com.song2.myapplication.data

data class MovieDataResponse (
    private val lastBuildDate : String?,
    private val total : Int?,
    private val start : Int?,
    private val items : ArrayList<MovieData>
)