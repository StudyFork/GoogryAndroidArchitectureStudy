package com.jay.architecturestudy.network

import com.jay.architecturestudy.data.model.ResponseBlog
import com.jay.architecturestudy.data.model.ResponseImage
import com.jay.architecturestudy.data.model.ResponseKin
import com.jay.architecturestudy.data.model.ResponseMovie
import io.reactivex.Single

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