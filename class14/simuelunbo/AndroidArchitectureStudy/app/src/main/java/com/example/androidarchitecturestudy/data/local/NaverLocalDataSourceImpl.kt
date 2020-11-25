package com.example.androidarchitecturestudy.data.local

import com.example.androidarchitecturestudy.MyApplication
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.QueryHistory

class NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun saveMovieData(movie: List<Movie>) = MyApplication.prefs.saveMovieList(movie)
    override fun getMovieData(): List<Movie>? = MyApplication.prefs.getMovieList()
    override fun saveMovieQuery(title: String) = MyApplication.prefs.saveMovieQuery(title)
    override fun getMovieQueryList(): List<QueryHistory> = MyApplication.prefs.getMovieQueryList()

}