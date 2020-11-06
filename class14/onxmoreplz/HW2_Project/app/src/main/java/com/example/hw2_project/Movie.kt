package com.example.hw2_project

data class MovieList( val items : List<Movie>)

data class Movie  (
    val image : String,
    val title : String,
    val pubDate : String,
    val director : String
)