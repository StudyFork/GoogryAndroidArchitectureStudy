package com.example.study.data.datasource.remote.network

import com.example.study.data.model.NaverSearchResponse
import retrofit2.Call
import retrofit2.http.*

interface NaverApiService {

/*    @Headers(
        "X-Naver-Client-Id: AZeVMtYlsaS7bdr8W7PX",
        "X-Naver-Client-Secret: a7hDdCsKST"
    )*/

    @GET("v1/search/movie.json")
    fun getMovieList(
        @Query("query") query: String
    ) : Call<NaverSearchResponse>
}