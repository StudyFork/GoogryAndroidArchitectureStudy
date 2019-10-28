package com.buddman1208.architecturestudy.utils

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkAPI {

    @GET("/v1/search/{searchType}.json")
    fun searchByType(
        @Path("searchType") searchType: String,
        @Query("query") query: String
    ): Observable<Response<HashMap<String, String>>>

}