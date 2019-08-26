package com.example.seonoh.seonohapp.network

interface BaseResult<in T> {
    fun getNetworkSuccess(result: T)
    fun getNetworkFailed(code: String)
}