package com.example.seonoh.seonohapp.network

interface BaseResult<in T> {
    fun onNetworkSuccess(result: T)
    fun onNetworkFailed(code: String)
}