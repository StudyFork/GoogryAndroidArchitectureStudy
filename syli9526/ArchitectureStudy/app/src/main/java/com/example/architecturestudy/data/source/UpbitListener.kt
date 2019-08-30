package com.example.architecturestudy.data.source

interface UpbitListener<T> {

    fun onResponse(dataList: List<T>)
    fun onFailure(str: String)

}