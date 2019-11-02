package com.buddman1208.architecturestudy.utils

import com.buddman1208.architecturestudy.models.CommonResponse
import io.reactivex.Observable
import retrofit2.Response


object NetworkManager {

    private var service = NetworkServiceGenerator.createService(NetworkApi::class.java)

    fun searchByType(
        searchType: String,
        query: String
    ): Observable<Response<CommonResponse>> = service.searchByType(searchType, query)
}