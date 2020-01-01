package com.jay.architecturestudy.network

import com.jay.architecturestudy.data.model.*
import com.jay.architecturestudy.util.singleIoMainThread
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Call

object Api {

    fun getMovies(keyword: String): Single<ResponseMovie> =
        ApiClient.apiService
            .getMovies(
                query = keyword
            )
            .map { ResponseMovie(it.items) }

    fun getImages(keyword: String): Single<ResponseImage> =
        ApiClient.apiService
            .getImages(
                query = keyword
            )
            .map { ResponseImage(it.items) }

    fun getBlog(keyword: String): Single<ResponseBlog> =
        ApiClient.apiService
            .getBlog(
                query = keyword
            )
            .map { ResponseBlog(it.items) }

    fun getKin(keyword: String): Single<ResponseKin> =
        ApiClient.apiService
            .getKin(
                query = keyword
            )
            .map { ResponseKin(it.items) }

}