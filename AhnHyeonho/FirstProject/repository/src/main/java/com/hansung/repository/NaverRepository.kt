package com.hansung.repository

import com.hansung.firstproject.data.MovieResponseModel

interface NaverRepository {
    fun getMoviesData(
        title: String,
        success: (MovieResponseModel) -> Unit,
        fail: (Throwable) -> Unit,
        isEmptyList: () -> Unit
    )
}