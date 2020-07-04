package com.example.study.data.repository

import com.example.study.data.model.NaverApiData

interface MovieListRepository {
    fun doSearch(query: String): List<NaverApiData.Item>
}