package com.example.aas.data.source.local

import android.content.Context

interface LocalDataSource {
    fun saveQuery(query: String, context: Context)

    fun getSavedQuery(context: Context): List<String>
}