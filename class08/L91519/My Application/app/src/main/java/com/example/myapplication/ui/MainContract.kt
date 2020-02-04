package com.example.myapplication.ui

import com.example.myapplication.Movie
import com.example.myapplication.MovieDatabase
import com.example.myapplication.data.model.MovieResult

interface MainContract {
    interface View
    {
        fun updateMovieRecycler(items: List<MovieResult.Item>)

        fun failMovieGet(msg: String)

        fun findMovie()

        fun queryNone()

        fun resultNone()

        fun resentData()

        fun saceCache()
    }

    interface Presenter
    {
        fun findMovie(query: String)

        fun resentData(db: MovieDatabase)

        fun saveCache(db: MovieDatabase, movies: List<MovieResult.Item>)

        fun delMovies(db: MovieDatabase)
    }
}