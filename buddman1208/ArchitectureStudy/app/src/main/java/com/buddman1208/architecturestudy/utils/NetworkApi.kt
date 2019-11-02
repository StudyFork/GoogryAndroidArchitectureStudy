package com.buddman1208.architecturestudy.utils

import com.buddman1208.architecturestudy.models.CommonResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("/v1/search/{searchType}.json")
    fun searchByType(
        @Path("searchType") searchType: String,
        @Query("query") query: String
    ): Observable<Response<CommonResponse>>

}