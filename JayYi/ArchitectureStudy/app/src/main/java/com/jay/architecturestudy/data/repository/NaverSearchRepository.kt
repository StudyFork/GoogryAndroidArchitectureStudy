package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.model.ResponseBlog
import com.jay.architecturestudy.data.model.ResponseImage
import com.jay.architecturestudy.data.model.ResponseKin
import com.jay.architecturestudy.data.model.ResponseMovie
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import io.reactivex.Single

interface NaverSearchRepository {

    val naverSearchRemoteDataSource: NaverSearchRemoteDataSource

    fun getMovie(keyword: String) : Single<ResponseMovie>

    fun getImage(keyword: String) : Single<ResponseImage>

    fun getBlog(keyword: String) : Single<ResponseBlog>

    fun getKin(keyword: String) : Single<ResponseKin>
}