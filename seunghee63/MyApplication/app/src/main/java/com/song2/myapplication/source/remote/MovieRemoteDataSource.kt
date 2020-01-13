package com.song2.myapplication.source.remote

import com.song2.myapplication.source.MovieData
import com.song2.myapplication.source.MovieDataResponse
import com.song2.myapplication.source.remote.network.NetworkServiceImpl
import retrofit2.Call
import retrofit2.Response

object MovieRemoteDataSource : MovieRemoteDataSourceImpl {

    private val network = NetworkServiceImpl.service

    override fun getMovieData(
        keyword: String,
        display: Int,
        start: Int,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        network.getMoreMovieSearch(keyword, display, start)
            .enqueue(object : retrofit2.Callback<MovieDataResponse> {
                override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<MovieDataResponse>,
                    response: Response<MovieDataResponse>
                ) {
                    onSuccess(response.body()!!.items)
                }

            })
    }


}