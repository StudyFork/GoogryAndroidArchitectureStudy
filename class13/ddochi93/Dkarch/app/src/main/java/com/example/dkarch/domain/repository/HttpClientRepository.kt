package com.example.dkarch.domain.repository

import okhttp3.OkHttpClient

interface HttpClientRepository {

    fun getAccessOkHttp(): OkHttpClient

}
