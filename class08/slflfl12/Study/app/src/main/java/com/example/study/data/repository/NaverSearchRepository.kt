package com.example.study.data.repository

import com.example.study.data.model.NaverSearchResponse
import io.reactivex.Single

interface NaverSearchRepository {
    fun getMovies(query: String) : Single<NaverSearchResponse>
}
