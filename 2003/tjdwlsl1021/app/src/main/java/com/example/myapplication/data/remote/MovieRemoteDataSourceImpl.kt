package com.example.myapplication.data.remote

import com.example.myapplication.model.MovieEntity
import com.example.myapplication.model.SearchMovieInfoResponse
import com.example.myapplication.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRemoteDataSourceImpl : MovieRemoteDataSource {

    override fun getMovieList(query: String, success: (List<MovieEntity>) -> Unit, failed: (Throwable) -> Unit) {
        val call = NetworkService.retrofitService.getMovieList(query, 10, 1, "1")

        call.enqueue(object : Callback<SearchMovieInfoResponse> {
            override fun onResponse(call: Call<SearchMovieInfoResponse>, response: Response<SearchMovieInfoResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()

                    result?.let {
                        success(result.items)
                    }
                }
            }

            override fun onFailure(call: Call<SearchMovieInfoResponse>, t: Throwable) {
                failed(t)
            }
        })
    }
}