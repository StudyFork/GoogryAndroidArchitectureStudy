package com.example.data.source.remote

import com.example.data.MovieApi


class RemoteDataSourceImpl : RemoteDataSource {

    override fun getMovieData(query: String) = MovieApi.retrofitService.requestMovieList(query)
}