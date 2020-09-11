package com.example.myproject.data.source.remote

import com.example.myproject.data.model.Items

interface NaverRemoteDataSource {
    fun getMovieList(
        title: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    )
}
