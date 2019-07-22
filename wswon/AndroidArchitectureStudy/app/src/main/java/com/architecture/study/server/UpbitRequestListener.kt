package com.architecture.study.server

interface UpbitRequestListener<T> {
    fun onSucess(dataList: List<T>)
    fun onEmpty(str: String)
    fun onFailure(str: String)
}