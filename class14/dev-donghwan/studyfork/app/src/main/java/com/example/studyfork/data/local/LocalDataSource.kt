package com.example.studyfork.data.local

interface LocalDataSource {
    fun putRecentSearchList(item: String)
    fun getRecentSearchList(): List<String>
}