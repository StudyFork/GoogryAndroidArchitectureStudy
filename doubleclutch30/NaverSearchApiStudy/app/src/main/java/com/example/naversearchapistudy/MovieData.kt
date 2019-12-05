package com.example.naversearchapistudy

data class MovieData (val items : List<MovieItems>)

data class MovieItems(
        val image : String,
        val title : String,
        val year : String,
        val link : String,
        val director : String,
        val actor: String)
