package com.jay.architecturestudy.network

import com.jay.architecturestudy.model.ResponseMovies
import retrofit2.Call

object Api {

    fun getMovies(keyword: String) : Call<ResponseMovies> {
        return ApiClient.getApiService()
            .getMovies(
                query = keyword
            )
    }
}