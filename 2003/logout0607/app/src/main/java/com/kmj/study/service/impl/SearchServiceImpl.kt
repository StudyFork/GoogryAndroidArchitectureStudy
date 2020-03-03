package com.kmj.study.service.impl

import com.kmj.study.dto.MovieResponseDto
import com.kmj.study.network.createRetrofit
import com.kmj.study.service.SearchService
import retrofit2.Call

class SearchServiceImpl : SearchService {
    private val searchService =
        createRetrofit(SearchService::class.java, "https://openapi.naver.com")

    override fun getSearchItems(
        clientId: String?,
        clientSecret: String?,
        userAgent: String?,
        display: String?,
        start: String?,
        query: String
    ): Call<MovieResponseDto> {
        return searchService.getSearchItems(query = query)
    }
}