package com.siwon.prj

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MovieSearchService {
    @GET("query{inputMovieName}")
    fun search()
}