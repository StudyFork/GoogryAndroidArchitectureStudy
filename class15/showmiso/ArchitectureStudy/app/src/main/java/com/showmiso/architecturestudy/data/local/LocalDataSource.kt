package com.showmiso.architecturestudy.data.local

interface LocalDataSource {

    fun addHistory(query: String)

    fun getHistory(): List<String>

    fun removeHistory(query: String)
}