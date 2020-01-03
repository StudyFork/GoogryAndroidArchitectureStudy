package com.example.androidarchitecture.data.datasource.local

import android.content.SharedPreferences
import com.example.androidarchitecture.data.datasource.database.SearchHistDatabase
import com.example.androidarchitecture.data.response.BlogData

interface NaverLocalDataSourceInterface {
    val spm: SharedPreferences
    val searchHistyDatabase: SearchHistDatabase

    fun saveBlogHist(blog: List<BlogData>)
    suspend fun getBlogHist() : List<BlogData>


    fun saveBlogKeyword(keyword: String)
    fun getBlogKeyword(): String
}