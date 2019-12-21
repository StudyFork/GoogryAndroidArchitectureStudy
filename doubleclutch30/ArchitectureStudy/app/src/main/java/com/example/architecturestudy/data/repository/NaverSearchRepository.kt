package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.BlogData
import com.example.architecturestudy.data.model.ImageData
import com.example.architecturestudy.data.model.KinData
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource

interface NaverSearchRepository  {
    val naverSearchRemoteDataSource : NaverSearchRemoteDataSource

    fun getMovie(keyword : String, success : (MovieData) -> Unit, fail : (Throwable) -> Unit)
    fun getBlog(keyword: String, success: (BlogData) -> Unit, fail: (Throwable) -> Unit)
    fun getKin(keyword: String, success: (KinData) -> Unit, fail: (Throwable) -> Unit)
    fun getImage(keyword: String, success: (ImageData) -> Unit, fail: (Throwable) -> Unit)

}