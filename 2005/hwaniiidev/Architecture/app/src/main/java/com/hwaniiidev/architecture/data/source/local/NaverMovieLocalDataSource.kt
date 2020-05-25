package com.hwaniiidev.architecture.data.source.local

import android.content.Context
import com.hwaniiidev.architecture.model.Item

interface NaverMovieLocalDataSource {
    fun cachingMovies(
        query: String,
        movies: ArrayList<Item>
    )
    /*fun getCachedMovies(
        미구현
    )*/
}