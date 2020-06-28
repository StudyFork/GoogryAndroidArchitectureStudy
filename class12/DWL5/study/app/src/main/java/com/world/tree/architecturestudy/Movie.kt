package com.world.tree.architecturestudy

class Movie (
    var lastBuildDate : String,
    var total : Int,
    var start : Int,
    var display : Int,
    var items : List<Items>
) {
    data class Items(
        val title:String,
        val link : String,
        val image : String,
        val subtitle : String,
        val pubDate : String,
        val director : String,
        val actor : String,
        val userRating: Double
    )
}