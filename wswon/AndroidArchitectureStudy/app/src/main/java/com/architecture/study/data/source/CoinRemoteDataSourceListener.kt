package com.architecture.study.network.request

interface UpbitRequestListener<T> {
    fun onSucess(dataList: List<T>)
    fun onEmpty(str: String)
    fun onFailure(str: String)
}