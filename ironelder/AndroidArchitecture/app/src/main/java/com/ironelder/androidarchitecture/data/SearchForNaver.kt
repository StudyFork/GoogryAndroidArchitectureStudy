package com.ironelder.androidarchitecture.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchForNaver {
    @GET("v1/search/{type}.json")
    fun requestSearchForNaver(@Path("type") type: String, @Query("query") query: String = "Test"): Call<TotalModel>
}