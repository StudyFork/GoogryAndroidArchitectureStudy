package com.example.myapplication.data.source.remote

import com.example.myapplication.MovieApi
import com.example.myapplication.data.model.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl(private val movieApi: MovieApi) :
    NaverRemoteDataSource {

    override fun getResultData(
        query: String,
        success: (MovieResult) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        movieApi.searchMovie(query).enqueue(object : Callback<MovieResult> {
            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                response.body()?.let {
                    success(it)
                }
            }

        })
    }
}