package com.example.myapplication.data.remote.source

import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.data.remote.network.NetworkService
import com.example.myapplication.data.remote.network.SearchMovieInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRemoteDataSourceImpl :
    MovieRemoteDataSource {

    override fun getMovieList(
        query: String,
        success: (List<MovieEntity>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        val call = NetworkService.retrofitService.getMovieList(query, 10, 1, "1")

        call.enqueue(object : Callback<SearchMovieInfoResponse> {
            override fun onResponse(
                call: Call<SearchMovieInfoResponse>,
                response: Response<SearchMovieInfoResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()

                    result?.let {
                        success(result.items)
                    }
                    if (result == null) {
                        failed(Throwable("오류"))
                    }
                }
            }

            override fun onFailure(call: Call<SearchMovieInfoResponse>, t: Throwable) {
                failed(t)
            }
        })
    }
}