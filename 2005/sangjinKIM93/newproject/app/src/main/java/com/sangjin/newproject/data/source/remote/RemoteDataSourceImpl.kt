package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.network.MovieApi

class RemoteDataSourceImpl : RemoteDataSource {

    override fun getMovieData(query: String) = MovieApi.retrofitService.requestMovieList(query)
}