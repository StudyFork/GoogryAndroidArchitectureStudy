package com.architecture.study.data.source.remote

interface CoinRemoteDataSourceListener<T> {
    fun onSuccess(dataList: List<T>)
    fun onEmpty(str: String)
    fun onFailure(str: String)
}