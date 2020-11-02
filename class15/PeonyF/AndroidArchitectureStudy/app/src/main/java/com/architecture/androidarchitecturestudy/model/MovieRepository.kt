package com.architecture.androidarchitecturestudy.model

import com.architecture.androidarchitecturestudy.webservice.ApiClient
import retrofit2.Call

class MovieRepository {
    fun getMovieData(keyword: String, cnt: Int): Call<MovieResponse> =
        ApiClient.NETWORK_SERVICE.getMovieSearch(keyword, cnt)
}