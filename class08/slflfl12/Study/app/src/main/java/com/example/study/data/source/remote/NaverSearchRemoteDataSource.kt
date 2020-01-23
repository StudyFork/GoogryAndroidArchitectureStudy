package com.example.study.data.source.remote

import com.example.study.data.model.NaverSearchResponse
import io.reactivex.Single

interface NaverSearchRemoteDataSource {
    fun getMovies(query: String): Single<NaverSearchResponse>
}