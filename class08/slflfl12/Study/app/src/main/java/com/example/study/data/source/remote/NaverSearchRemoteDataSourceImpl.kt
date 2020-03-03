package com.example.study.data.source.remote

import com.example.study.data.model.NaverSearchResponse
import com.example.study.data.source.local.SearchResultDatabase
import com.example.study.data.source.local.model.SearchResult
import com.example.study.data.source.remote.network.NaverApiService
import com.google.gson.Gson
import io.reactivex.Single

class NaverSearchRemoteDataSourceImpl(private val naverApiService: NaverApiService) :
    NaverSearchRemoteDataSource {

    override fun getMovies(
        query: String
    ): Single<NaverSearchResponse> {
        return naverApiService.getMovieList(query)
    }
}