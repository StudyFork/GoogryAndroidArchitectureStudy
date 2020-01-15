package com.example.study.data.datasource.remote

import com.example.study.data.datasource.remote.network.NaverApiClient
import com.example.study.data.model.NaverSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {

    override fun getMovies(
        query: String,
        success: (NaverSearchResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NaverApiClient.naverRetrofitService.getMovieList(query)
            .enqueue(object : Callback<NaverSearchResponse> {
                override fun onFailure(call: Call<NaverSearchResponse>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<NaverSearchResponse>,
                    response: Response<NaverSearchResponse>
                ) {
                    response.body()?.let {
                        success(it)
                    }
                }
            })
    }
}