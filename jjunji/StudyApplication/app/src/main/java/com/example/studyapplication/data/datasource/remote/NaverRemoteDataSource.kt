package com.example.studyapplication.data.datasource.remote

import com.example.studyapplication.network.Conn

interface NaverRemoteDataSource {
    fun getMovieList(title : String, conn : Conn)
}