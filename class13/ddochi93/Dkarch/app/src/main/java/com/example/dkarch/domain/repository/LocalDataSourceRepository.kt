package com.example.dkarch.domain.repository

interface LocalDataSourceRepository {
    fun saveQuery(query: String)

    fun getSavedQueryList(): List<String>
}
