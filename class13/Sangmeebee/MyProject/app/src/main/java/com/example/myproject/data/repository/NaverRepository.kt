package com.example.myproject.data.repository

import com.example.myproject.data.model.Items

interface NaverRepository {
    fun getMovieList(
        title: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    )

    fun saveRecentSearchTitle(title: String)

    fun readRecentSearchTitle(): ArrayList<String>
}
