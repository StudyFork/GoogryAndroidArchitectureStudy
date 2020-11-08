package com.showmiso.architecturestudy.data.repository

interface NaverRepository {
    fun getMovies(query: String)
}