package com.example.study.data.source.remote

import com.example.study.data.model.NaverSearchResponse
import com.example.study.data.source.local.SearchResultDatabase
import io.reactivex.Single

interface NaverSearchRemoteDataSource {
    fun getMovies(query: String, searchResultDatabase: SearchResultDatabase): Single<NaverSearchResponse>
}