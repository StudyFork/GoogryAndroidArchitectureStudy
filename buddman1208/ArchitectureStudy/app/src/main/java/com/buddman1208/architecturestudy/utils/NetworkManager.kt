package com.buddman1208.architecturestudy.utils

import com.buddman1208.architecturestudy.models.CommonResponse
import io.reactivex.Single


object NetworkManager {

    private var service = NetworkServiceGenerator.createService(NetworkApi::class.java)

    fun searchByType(
        searchType: String,
        query: String
    ): Single<CommonResponse> = service.searchByType(searchType, query)
}