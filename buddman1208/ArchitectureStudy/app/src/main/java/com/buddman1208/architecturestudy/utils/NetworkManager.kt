package com.buddman1208.architecturestudy.utils

import io.reactivex.Observable
import retrofit2.Response


object NetworkManager {

    private var service = NetworkServiceGenerator.createService(NetworkAPI::class.java)

    fun searchByType(
        searchType: String,
        query: String
    ): Observable<Response<HashMap<String, String>>> = service.searchByType(searchType, query)
}