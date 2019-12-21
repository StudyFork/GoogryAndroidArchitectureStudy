package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource

interface NaverSearchRepository  {
    val naverSearchRemoteDataSource : NaverSearchRemoteDataSource

    fun getMovie(keyword : String, success : (MovieData) -> Unit, fail : (Throwable) -> Unit)

}