package com.example.androidarchitecturestudy.data.datasource

import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.room.SearchedDataBase

interface MovieLocalDataSource {
    fun getRecentSearchedMovieList()
    fun saveRecentSearch(searchQuery:String,database:SearchedDataBase)
}