package com.tsdev.domain.repository

interface NaverMovieRepository  {
    fun getMovieList(query: String): Single<List<Item>>
}