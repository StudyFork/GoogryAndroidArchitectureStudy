package com.hhi.myapplication.data.local

interface NaverLocalDataSource {
    fun saveQuery(query: String)

    fun getQueryList(): List<String>
}