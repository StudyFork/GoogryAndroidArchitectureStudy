package com.example.architecturestudy.data.source.local

interface NaverSearchLocalDataSource {

    fun <T> saveSearchItems(items: List<T>)

    fun <T> getSearchItems(
        keyword: String?,
        success: (items: List<T>) -> Unit,
        fail: (Throwable) -> Unit
    )
}