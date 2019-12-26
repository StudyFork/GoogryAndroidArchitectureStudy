package com.song2.myapplication.data

import com.song2.myapplication.network.NetworkServiceImpl.service
import retrofit2.Call

class MovieRepository{
    fun getMovieData(clientId: String, secret : String, keyword : String , cnt : Int): Call<GetMovieDataResponse> =
        service.getMovieSearch(clientId, secret, keyword, cnt)
}