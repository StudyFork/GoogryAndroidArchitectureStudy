package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.model.MovieData

interface NaverSearchRemoteDataSource {

    fun getMovie(keyword : String, success : (MovieData) -> Unit, fail : (Throwable) -> Unit)

}