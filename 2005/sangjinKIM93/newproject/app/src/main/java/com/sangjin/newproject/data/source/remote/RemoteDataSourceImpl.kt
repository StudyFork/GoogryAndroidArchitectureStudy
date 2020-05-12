package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.MovieApi
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.NaverMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {
    override fun getMovieData(
        query: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        MovieApi.retrofitService.requestMovieList(query)
            .enqueue(object : Callback<NaverMovieResponse>{
                override fun onResponse(
                    call: Call<NaverMovieResponse>,
                    response: Response<NaverMovieResponse>
                ) {
                    val movies = response.body()?.items

                    movies?.let {
                        onSuccess(it)
                    }
                }

                override fun onFailure(call: Call<NaverMovieResponse>, t: Throwable) {
                    onFailure(t)
                }

            })
    }
}