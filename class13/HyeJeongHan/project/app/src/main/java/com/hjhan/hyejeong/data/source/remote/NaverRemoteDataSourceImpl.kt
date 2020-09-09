package com.hjhan.hyejeong.data.source.remote

import com.hjhan.hyejeong.data.model.MovieData
import com.hjhan.hyejeong.data.network.NaverApi
import com.hjhan.hyejeong.data.network.ServiceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    override fun getSearchMovies(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    ) {
        val api = ServiceClient.createService(NaverApi::class.java)
        api.getMovies(query, 10, 100).enqueue(object : Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        success(it)
                    }
                } else {
                    failed("네트워크 통신 실패")
                }

            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                failed("네트워크 통신 실패")
            }
        })
    }
}