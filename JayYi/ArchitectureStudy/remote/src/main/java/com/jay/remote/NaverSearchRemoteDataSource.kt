package com.jay.remote

import com.jay.remote.model.ResponseBlog
import com.jay.remote.model.ResponseImage
import com.jay.remote.model.ResponseKin
import com.jay.remote.model.ResponseMovie
import io.reactivex.Single

interface NaverSearchRemoteDataSource {

    fun getMovie(keyword: String): Single<ResponseMovie>

    fun getImage(keyword: String): Single<ResponseImage>

    fun getBlog(keyword: String): Single<ResponseBlog>

    fun getKin(keyword: String): Single<ResponseKin>
}