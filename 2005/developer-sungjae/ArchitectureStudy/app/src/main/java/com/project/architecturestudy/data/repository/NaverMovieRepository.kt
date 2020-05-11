package com.project.architecturestudy.data.repository

interface NaverMovieRepository {

    fun getMovieList(query: String)
}