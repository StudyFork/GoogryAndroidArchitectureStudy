package com.example.handnew04.data.remote

import com.example.handnew04.data.NaverMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataResourceImpl : MovieRemoteDataResource {
    override fun getMovieData(
        query: String,
        success: (NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        val searchLimit = 100

        retrofitService().requestSearchMovie(query, searchLimit)
            .enqueue(object : Callback<NaverMovieResponse> {
                override fun onFailure(call: Call<NaverMovieResponse>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<NaverMovieResponse>,
                    response: Response<NaverMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        success(response.body() as NaverMovieResponse)
                    }
                }
            })
    }
}