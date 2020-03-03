package com.kmj.study.repository

import com.kmj.study.dto.MovieResponseDto
import com.kmj.study.service.SearchDataService
import com.kmj.study.service.impl.SearchServiceImpl
import retrofit2.Call

object SearchRepository : SearchDataService {
    private val searchServiceImpl = SearchServiceImpl()

    override fun getSearchItems(query: String): Call<MovieResponseDto> {
        return searchServiceImpl.getSearchItems(query = query)
    }
}