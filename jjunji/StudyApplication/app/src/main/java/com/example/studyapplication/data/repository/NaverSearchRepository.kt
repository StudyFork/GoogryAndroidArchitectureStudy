package com.example.studyapplication.data.repository

import com.example.studyapplication.network.Conn

interface NaverSearchRepository {
    fun getMovieList(query : String, conn : Conn)
}