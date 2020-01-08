package com.example.studyapplication.data.datasource.local

import com.example.studyapplication.data.model.MovieInfo

interface NaverLocalDataSource {
    fun saveMovieList(arrItem: ArrayList<MovieInfo>)

    fun deleteMovieList()

    fun getCacheMovieList(success : (ArrayList<MovieInfo>) -> Unit, failed : (Throwable) -> Unit)
}