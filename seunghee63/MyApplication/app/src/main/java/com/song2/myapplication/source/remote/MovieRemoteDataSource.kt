package com.song2.myapplication.source.remote

import com.song2.myapplication.source.MovieDataResponse
import com.song2.myapplication.source.remote.network.NetworkServiceImpl
import retrofit2.Call
import retrofit2.Response

object MovieRemoteDataSource : MovieRemoteDataSourceImpl {

    val network = NetworkServiceImpl.service

    override fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieDataResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        network.getMovieSearch(keyword, display)
            .enqueue(object : retrofit2.Callback<MovieDataResponse> {
                override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MovieDataResponse>,
                    response: Response<MovieDataResponse>
                ) {

                }

            })
    }


}