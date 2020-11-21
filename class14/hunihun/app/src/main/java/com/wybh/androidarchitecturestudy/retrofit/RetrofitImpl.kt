package com.wybh.androidarchitecturestudy.retrofit

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitImpl {
    
    @GET("v1/search/movie.json")
    fun getCinemaData(@Query("query") query: String?): Single<ResponseCinemaData>
}