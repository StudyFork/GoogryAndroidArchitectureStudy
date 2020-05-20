package com.hwaniiidev.architecture.data.source.local

import android.content.Context
import com.hwaniiidev.architecture.model.Item

interface NaverMovieLocalDataSource {
    fun cachingMovies(
        context: Context,
        query: String,
        movies: ArrayList<Item>
    )
    /*fun getCachedMovies(

    )
    fun removeOverlap(
        query: String,
        cachingMovies: ArrayList<Item>
    ):ArrayList<Item>*/
}