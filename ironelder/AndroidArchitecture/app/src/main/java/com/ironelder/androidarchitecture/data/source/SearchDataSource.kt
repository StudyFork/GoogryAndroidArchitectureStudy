package com.ironelder.androidarchitecture.data.source

interface SearchDataSource<T, R> {
    fun getDataForSearch(
        type: String,
        query: String,
        success: (result: T) -> Unit,
        fail: (msg: R) -> Unit
    )
}