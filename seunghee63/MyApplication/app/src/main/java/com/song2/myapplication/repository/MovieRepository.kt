package com.song2.myapplication.repository

import com.song2.myapplication.source.MovieDataResponse
import com.song2.myapplication.source.remote.NetworkServiceImpl
import retrofit2.Call

class MovieRepository{
    fun getMovieData(keyword : String , cnt : Int): Call<MovieDataResponse> =
        NetworkServiceImpl.service.getMovieSearch(keyword, cnt)
}