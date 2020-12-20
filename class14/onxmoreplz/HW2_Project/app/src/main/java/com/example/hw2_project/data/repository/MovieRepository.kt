package com.example.hw2_project.data.repository

import com.example.hw2_project.data.api.NaverMovieData

interface MovieRepository {

    fun getMovieList(
        query: String,
        success: (NaverMovieData.NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun saveQuery(query: String)

    fun getSavedQuery(): List<String>
}