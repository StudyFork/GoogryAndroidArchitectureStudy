package com.god.taeiim.myapplication.api

import com.god.taeiim.myapplication.api.model.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("search/{searchType}.json")
    fun searchContents(@Path("searchType") searchType: String, @Query("query") query: String): Call<SearchResult>
}