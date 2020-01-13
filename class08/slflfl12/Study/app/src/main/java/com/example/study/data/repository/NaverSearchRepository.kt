package com.example.study.data.repository

import com.example.study.data.model.NaverSearchResponse

interface NaverSearchRepository {

    fun getMovies(query:String, success: (NaverSearchResponse) -> Unit, fail: (Throwable) -> Unit)
}
