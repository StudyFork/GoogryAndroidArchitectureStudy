package com.olaf.nukeolaf.data.remote

import com.olaf.nukeolaf.data.model.MovieResponse
import com.olaf.nukeolaf.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {

    private val retrofitClient = RetrofitClient.client

    override fun getMovies(
        query: String,
        callback: MovieRemoteDataSource.LoadMoviesCallback
    ) {
        val options: MutableMap<String, Any> = mutableMapOf(
            "query" to query,
            "display" to 10,
            "start" to 1
        )
        retrofitClient.searchMovie(options).enqueue(object : Callback<MovieResponse> {
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