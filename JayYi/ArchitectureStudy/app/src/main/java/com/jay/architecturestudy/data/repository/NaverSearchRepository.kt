package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.model.ResponseBlog
import com.jay.architecturestudy.data.model.ResponseImage
import com.jay.architecturestudy.data.model.ResponseKin
import com.jay.architecturestudy.data.model.ResponseMovie
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSource

interface NaverSearchRepository {

    val naverSearchRemoteDataSource: NaverSearchRemoteDataSource

    fun getMovie(keyword: String, success: (ResponseMovie) -> Unit, fail: (Throwable) -> Unit)

    fun getImage(keyword: String, success: (ResponseImage) -> Unit, fail: (Throwable) -> Unit)

    fun getBlog(keyword: String, success: (ResponseBlog) -> Unit, fail: (Throwable) -> Unit)

    fun getKin(keyword: String, success: (ResponseKin) -> Unit, fail: (Throwable) -> Unit)
}