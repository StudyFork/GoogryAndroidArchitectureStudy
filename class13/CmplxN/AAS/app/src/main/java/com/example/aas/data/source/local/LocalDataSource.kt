package com.example.aas.data.source.local

interface LocalDataSource {
    fun saveQuery(query: String)

    fun getSavedQuery(): List<String>
}