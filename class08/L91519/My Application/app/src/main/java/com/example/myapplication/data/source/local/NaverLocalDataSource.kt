package com.example.myapplication.data.source.local

import com.example.myapplication.data.model.Movie

interface NaverLocalDataSource {
    fun getRecentData(): Movie

    fun saveCache(movies: Movie)

    fun delMovie()
}