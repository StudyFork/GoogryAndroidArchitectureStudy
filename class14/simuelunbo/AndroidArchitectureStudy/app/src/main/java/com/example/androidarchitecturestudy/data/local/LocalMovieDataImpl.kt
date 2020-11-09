package com.example.androidarchitecturestudy.data.local

import com.example.androidarchitecturestudy.MyApplication
import com.example.androidarchitecturestudy.data.model.Movie

class LocalMovieDataImpl : LocalMovieData {
    override fun saveMovieData(movie: List<Movie>) = MyApplication.prefs.saveMovieList(movie)
    override fun getMovieData(): List<Movie>? = MyApplication.prefs.getMovieList()

}