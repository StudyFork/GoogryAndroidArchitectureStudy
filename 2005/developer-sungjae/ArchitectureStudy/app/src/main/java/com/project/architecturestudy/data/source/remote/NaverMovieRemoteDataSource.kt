package com.project.architecturestudy.data.source.remote

interface NaverMovieRemoteDataSource {

    fun getMovieList(query: String)
}