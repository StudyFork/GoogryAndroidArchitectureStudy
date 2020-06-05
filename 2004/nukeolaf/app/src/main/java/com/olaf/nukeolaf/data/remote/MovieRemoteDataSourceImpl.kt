package com.olaf.nukeolaf.data.remote

import com.olaf.nukeolaf.data.model.MovieResponse
import com.olaf.nukeolaf.network.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val movieApi: RetrofitInterface
) : MovieRemoteDataSource {

    override fun getMovies(
        query: String,
        callback: MovieRemoteDataSource.LoadMoviesCallback
    ) {
        val options = mapOf(
            "query" to query,
            "display" to 10,
            "start" to 1
        )
        movieApi.searchMovie(options).enqueue(object : Callback<MovieResponse> {
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