package com.example.androidarchitecturestudy.data.remote

import com.example.androidarchitecturestudy.data.model.MovieData

interface RemoteMovieData {

    fun getSearchMovieList(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    )

}