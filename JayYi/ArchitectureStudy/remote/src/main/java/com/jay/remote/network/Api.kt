package com.jay.remote.network

import com.jay.remote.model.ResponseBlog
import com.jay.remote.model.ResponseImage
import com.jay.remote.model.ResponseKin
import com.jay.remote.model.ResponseMovie
import com.jay.remote.network.service.ApiService
import io.reactivex.Single

internal class Api(
    private val apiService: ApiService
) {

    fun getMovies(keyword: String): Single<ResponseMovie> =
        apiService
            .getMovies(
                query = keyword
            )
            .map { ResponseMovie(it.items) }

    fun getImages(keyword: String): Single<ResponseImage> =
        apiService
            .getImages(
                query = keyword
            )
            .map { ResponseImage(it.items) }

    fun getBlog(keyword: String): Single<ResponseBlog> =
        apiService
            .getBlog(
                query = keyword
            )
            .map { ResponseBlog(it.items) }

    fun getKin(keyword: String): Single<ResponseKin> =
        apiService
            .getKin(
                query = keyword
            )
            .map { ResponseKin(it.items) }

}