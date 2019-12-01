package com.jay.architecturestudy.network

import com.jay.architecturestudy.model.ResponseImages
import com.jay.architecturestudy.model.ResponseMovies
import retrofit2.Call

object Api {

    fun getMovies(keyword: String) : Call<ResponseMovies> {
        return ApiClient.getApiService()
            .getMovies(
                query = keyword
            )
    }

    fun getImages(keyword: String): Call<ResponseImages> {
        return ApiClient.getApiService()
            .getImages(
                query = keyword
            )
    }
}