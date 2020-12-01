package com.example.androidarchitecturestudy.data.repository

import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.room.SearchedDataBase

interface MovieRepository {


    fun getMovieSearchResult(
        movieName: String,
        onSuccess: (GetMovieInfo.MovieList) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun saveRecentSearch(searchQuery:String,dataBase: SearchedDataBase)

}