package com.example.archstudy.data.source.local

import com.example.archstudy.MovieData

interface NaverQueryLocalDataSource {
    fun getMovie(query: String): MovieData
}