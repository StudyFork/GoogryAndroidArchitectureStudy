package com.example.architecturestudy.util

interface UpbitListener<T> {

    fun onResponse(dataList: List<T>)
    fun onFailure(str: String)

}