package com.jay.architecturestudy.network

import com.jay.architecturestudy.data.model.*
import retrofit2.Call

object Api {

    fun getMovies(keyword: String): Call<ResponseNaverQuery<Movie>> =
        ApiClient.apiService
            .getMovies(
                query = keyword
            )


    fun getImages(keyword: String): Call<ResponseNaverQuery<Image>> =
        ApiClient.apiService
            .getImages(
                query = keyword
            )


    fun getBlog(keyword: String): Call<ResponseNaverQuery<Blog>> =
        ApiClient.apiService
            .getBlog(
                query = keyword
            )


    fun getKin(keyword: String): Call<ResponseNaverQuery<Kin>> =
        ApiClient.apiService
            .getKin(
                query = keyword
            )
}