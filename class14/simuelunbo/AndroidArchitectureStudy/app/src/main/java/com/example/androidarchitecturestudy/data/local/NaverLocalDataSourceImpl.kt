package com.example.androidarchitecturestudy.data.local

import com.example.androidarchitecturestudy.MyApplication
import com.example.androidarchitecturestudy.data.model.Movie

class NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun saveMovieData(movie: List<Movie>) = MyApplication.prefs.saveMovieList(movie)
    override fun getMovieData(): List<Movie>? = MyApplication.prefs.getMovieList()
    override fun saveMovieTitle(title: String) = MyApplication.prefs.saveMovieTitle(title)
    override fun getMovieTitleList(): List<String> = MyApplication.prefs.getMovieTitleList()

}