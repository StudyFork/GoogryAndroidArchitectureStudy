package com.hansung.firstproject

import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.network.NaverApiServiceImpl.service
import retrofit2.Call

class SearchResult {
    fun getResult(
        clientId: String,
        clientSecret: String,
        keyword: String,
        display: Int
    ): Call<MovieResponseModel> = service.getRepoList(clientId, clientSecret, keyword, display)
}