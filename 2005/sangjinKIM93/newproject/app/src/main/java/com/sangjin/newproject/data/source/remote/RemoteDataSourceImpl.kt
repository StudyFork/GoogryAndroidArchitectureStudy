package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.MovieApi
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.NaverMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

    override fun getMovieData(query: String) = MovieApi.retrofitService.requestMovieList(query)
}