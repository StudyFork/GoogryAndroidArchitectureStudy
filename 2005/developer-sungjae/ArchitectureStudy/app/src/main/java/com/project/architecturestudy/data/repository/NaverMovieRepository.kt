package com.project.architecturestudy.data.repository

import com.project.architecturestudy.adapters.SearchAdapter

interface NaverMovieRepository {

    fun getMovieList(keyWord: String, adapter: SearchAdapter?)
}