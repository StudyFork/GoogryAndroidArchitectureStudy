package com.example.myapplication.data.source

import com.example.myapplication.ApiClient
import com.example.myapplication.data.model.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NaverRemoteDataImpl : NaverRemoteData {
    override fun getResultData(
        query: String,
        success: (MovieResult) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        ApiClient.getService.searchMovie(query).enqueue(object : Callback<MovieResult> {
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