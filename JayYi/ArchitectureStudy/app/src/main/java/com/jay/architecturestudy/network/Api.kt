package com.jay.architecturestudy.network

import com.jay.architecturestudy.model.ResponseBlog
import com.jay.architecturestudy.model.ResponseImages
import com.jay.architecturestudy.model.ResponseKin
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

    fun getBlog(keyword: String): Call<ResponseBlog> {
        return ApiClient.getApiService()
            .getBlog(
                query = keyword
            )
    }

    fun getKin(keyword: String): Call<ResponseKin> {
        return ApiClient.getApiService()
            .getKin(
                query = keyword
            )
    }
}