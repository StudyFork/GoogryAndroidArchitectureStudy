package com.kmj.study.service

import com.kmj.study.dto.MovieResponseDto
import retrofit2.Call

interface SearchDataService {
    fun getSearchItems(query: String): Call<MovieResponseDto>
}