package com.example.myapplication.data.source

import com.example.myapplication.Movie

interface NaverLocalDataSource {
    fun getRecentData(): Movie

    fun saveCache(movies: Movie)

    fun delMovie()
}