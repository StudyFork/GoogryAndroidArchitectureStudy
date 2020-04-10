package com.example.kyudong3.network

import com.example.kyudong3.BuildConfig

class NaverApiServiceImpl {
    private val naverService: NaverApiService

    private val clientId: String = BuildConfig.clientId
    private val clientSecret: String = BuildConfig.clientSecret

    init {
        naverService = RetrofitClient.getNaverService()
    }

    fun getSearchMovie(searchQuery: String) =
        naverService.getSearchMovie(clientId, clientSecret, searchQuery)
}