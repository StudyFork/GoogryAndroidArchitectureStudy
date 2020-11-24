package com.deepco.studyfork.data.local

interface LocalMovieData {
    fun saveQuery(query: String)

    fun getQueryList(): List<String>
}