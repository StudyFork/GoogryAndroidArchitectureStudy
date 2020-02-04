package com.example.myapplication.data.source

import com.example.myapplication.Movie
import com.example.myapplication.MovieDatabase

interface NaverLocalDataSource {
    fun getResentData(
        db: MovieDatabase
    )

    fun saveCache(db: MovieDatabase, movies: List<Movie>)

    fun delMovie(db: MovieDatabase)
}