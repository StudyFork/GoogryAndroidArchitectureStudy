package com.example.aas.data.source.local

import io.reactivex.Single

interface LocalDataSource {
    fun saveQuery(query: String)

    fun getSavedQuery(): Single<List<String>>

    fun onDestroy()
}