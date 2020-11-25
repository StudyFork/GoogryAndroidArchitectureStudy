package com.example.hw2_project.data.local

interface MovieLocalDataSource {
    fun saveQuery(query : String)

    fun getSavedQuery() : List<String>
}