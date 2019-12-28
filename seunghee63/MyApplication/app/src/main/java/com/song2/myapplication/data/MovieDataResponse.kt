package com.song2.myapplication.data

data class MovieDataResponse (
    var lastBuildDate : String?,
    var total : Int?,
    var start : Int?,
    var items : ArrayList<MovieData>
)