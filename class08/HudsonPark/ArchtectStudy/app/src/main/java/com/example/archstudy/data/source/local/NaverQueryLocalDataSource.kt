package com.example.archstudy.data.source.local

interface NaverQueryLocalDataSource {
    fun requestSearchWord(): String
    fun requestLocalData(query: String): MutableList<MovieData>?
    fun insertLocalData(query: String, list: MutableList<MovieData>): Unit?
}