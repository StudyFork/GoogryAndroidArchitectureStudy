package com.eunice.eunicehong.data.source.remote

import com.eunice.eunicehong.data.model.MovieContents
import com.eunice.eunicehong.data.source.MovieDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MovieRemoteDataSource(private val service: MovieService) : MovieDataSource {
    fun getMovieList(query: String, callback: MovieDataSource.LoadMoviesCallback) {
        service.getMovieList(query).enqueue(object : Callback<MovieContents> {
            override fun onResponse(call: Call<MovieContents>, response: Response<MovieContents>) {
                val content = response.body()
                if (response.isSuccessful && content != null) {
                    callback.onSuccess(query, content)
                } else {
                    callback.onFailure(HttpException(response))
                }
            }

            override fun onFailure(call: Call<MovieContents>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}