package com.example.myapplication.data.source

import android.content.Context
import com.example.myapplication.data.ApiClient
import com.example.myapplication.data.model.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl(val context: Context) : NaverRemoteDataSource {

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

    companion object {
        private var instance: NaverRemoteDataSourceImpl? = null
        fun getInstance(context: Context): NaverRemoteDataSourceImpl {
            return instance ?: NaverRemoteDataSourceImpl(
                context
            ).apply { instance = this }
        }
    }
}