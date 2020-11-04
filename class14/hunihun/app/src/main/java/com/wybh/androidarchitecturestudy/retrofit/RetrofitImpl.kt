package com.wybh.androidarchitecturestudy.retrofit

import com.wybh.androidarchitecturestudy.model.ResponseCinemaData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitImpl {
    
    @GET("v1/search/movie.json")
    fun getCinemaData(@Query("query") query: String?): Observable<ResponseCinemaData>
}