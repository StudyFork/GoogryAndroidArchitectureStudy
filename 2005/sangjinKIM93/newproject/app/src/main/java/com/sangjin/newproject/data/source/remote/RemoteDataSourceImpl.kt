package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.MovieApi
import com.sangjin.newproject.adapter.Movie
import com.sangjin.newproject.adapter.ResponseData
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
            .enqueue(object : Callback<ResponseData>{
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    val movies = response.body()?.items

                    movies?.let {
                        onSuccess(it)
                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    onFailure(t)
                }

            })
    }
}