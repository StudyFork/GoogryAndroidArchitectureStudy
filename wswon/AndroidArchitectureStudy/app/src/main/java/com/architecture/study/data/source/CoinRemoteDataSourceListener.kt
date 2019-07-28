package com.architecture.study.data.source

interface CoinRemoteDataSourceListener<T> {
    fun onSucess(dataList: List<T>)
    fun onEmpty(str: String)
    fun onFailure(str: String)
}