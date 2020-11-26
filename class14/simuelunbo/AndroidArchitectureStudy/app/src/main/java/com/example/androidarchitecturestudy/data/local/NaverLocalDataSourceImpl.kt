package com.example.androidarchitecturestudy.data.local

import com.example.androidarchitecturestudy.MyApplication
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.QueryHistory

class NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun saveMovieData(movie: List<Movie>) = MyApplication.prefs.saveMovieList(movie)
    override fun getMovieData(): List<Movie>? = MyApplication.prefs.getMovieList()
    override fun saveMovieQuery(title: String) {
        val titleList = getMovieQueryList()
        titleList.add(QueryHistory(title))
        if (titleList.size > 5) {
            titleList.removeAt(0)
        }
        MyApplication.prefs.saveMovieQuery(titleList)
    }
    override fun getMovieQueryList(): ArrayList<QueryHistory> = MyApplication.prefs.getMovieQueryList()

}