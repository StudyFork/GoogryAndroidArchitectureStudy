package com.example.study.data.repository

import com.example.study.data.model.NaverApiData

interface MovieListRepository {
    fun doSearch(
        query: String,
        response: (List<NaverApiData.Item>) -> Unit,
        fail: (Throwable) -> Unit
    )
}