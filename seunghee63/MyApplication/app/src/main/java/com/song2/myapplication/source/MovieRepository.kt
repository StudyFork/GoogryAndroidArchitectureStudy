package com.song2.myapplication.source

import com.song2.myapplication.source.remote.network.NetworkServiceImpl
import retrofit2.Call

class MovieRepository {
    fun getMovieData(keyword: String, cnt: Int): Call<MovieDataResponse> =
        NetworkServiceImpl.service.getMovieSearch(keyword, cnt)
}