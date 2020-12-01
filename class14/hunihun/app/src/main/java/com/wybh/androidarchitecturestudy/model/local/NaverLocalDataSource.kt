package com.wybh.androidarchitecturestudy.model.local

interface NaverLocalDataSource {
    fun saveSearchWord(word: String)
    fun getSearchWord(): String?
}