package com.practice.achitecture.myproject.network

import com.practice.achitecture.myproject.model.ResultOfSearchingModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    /**
     * @Path category = movie, book, blog, news 4가지 유형에서 검색할 수 있습니다.
     */
    @GET("v1/search/{category}.json")
    fun searchSomething(
        @Path("category") category: String,
        @Query("query") query: String
    ): Call<ResultOfSearchingModel>

}