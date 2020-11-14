package com.hhi.myapplication.data.remote

import com.hhi.myapplication.api.NaverAPI
import com.hhi.myapplication.data.model.MovieData
import retrofit2.Call
import retrofit2.Response

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {
    private val api = NaverAPI.create()
    override fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        api.searchMovies(query).enqueue(object : retrofit2.Callback<MovieData.Response> {
            override fun onResponse(
                call: Call<MovieData.Response>,
                response: Response<MovieData.Response>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        success(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieData.Response>, t: Throwable) {
                failed(t)
            }
        })
    }
}