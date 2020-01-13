package com.example.study.data.repository

import com.example.study.data.model.NaverSearchResponse

class NaverSearchRepositoryImpl : NaverSearchRepository {

    private val naverSearchRepository : NaverSearchRepository = NaverSearchRepositoryImpl()

    override fun getMovies(
        query: String,
        success: (NaverSearchResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRepository.getMovies(query, success, fail)
    }
}