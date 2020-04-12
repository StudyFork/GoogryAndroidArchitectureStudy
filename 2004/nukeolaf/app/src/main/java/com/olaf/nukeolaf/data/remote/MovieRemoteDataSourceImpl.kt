package com.olaf.nukeolaf.data.remote

import com.olaf.nukeolaf.MovieResponse
import com.olaf.nukeolaf.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {

    private val retrofitClient = RetrofitClient.client

    override fun getMovie(
        query: String,
        callback: MovieRemoteDataSource.LoadMoviesCallback
    ) {
        retrofitClient.searchMovie(query).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val body = response.body()
                if (body != null && response.isSuccessful) {
                    callback.onMoviesLoaded(body)
                } else {
                    callback.onResponseError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}