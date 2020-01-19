package com.example.myapplication.ui

import com.example.myapplication.data.model.MovieResult

interface MainConstract {
    interface View
    {
        fun updateMovieRecycler(items: List<MovieResult.Item>)

        fun failMovieGet(msg: String)

        fun findMovie(query: String)
    }

    interface Presenter
    {
        fun findMovie(query: String)
    }
}