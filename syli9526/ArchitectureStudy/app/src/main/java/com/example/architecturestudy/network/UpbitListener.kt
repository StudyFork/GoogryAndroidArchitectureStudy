package com.example.architecturestudy.network

interface UpbitListener<T> {

    fun onResponse(dataList: List<T>)
    fun onFailure(str: String)

}