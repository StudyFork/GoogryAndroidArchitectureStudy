package com.example.myproject.data.source.local

interface NaverLocalDataSource {
    fun saveRecentSearchTitle(title: String)
    fun readRecentSearchTitle(): ArrayList<String>
}
