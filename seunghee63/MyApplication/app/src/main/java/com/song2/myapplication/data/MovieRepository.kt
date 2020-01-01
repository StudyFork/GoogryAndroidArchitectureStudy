package com.song2.myapplication.data

import com.song2.myapplication.network.NetworkServiceImpl
import retrofit2.Call

class MovieRepository{
    fun getMovieData(keyword : String , cnt : Int): Call<MovieDataResponse> =
        NetworkServiceImpl.service.getMovieSearch(keyword, cnt)
}