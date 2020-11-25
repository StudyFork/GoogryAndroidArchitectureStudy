package com.example.hw2_project.data.local

import com.example.hw2_project.App

class MovieLocalDataSourceImpl : MovieLocalDataSource {
    override fun saveQuery(query: String) {
        App.prefs.saveQuery(query)
    }

    override fun getSavedQuery(): List<String> {
        return App.prefs.getSavedQueryList()
    }
}