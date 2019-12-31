package com.example.architecture_project.data.datasource.remote

import com.example.architecture_project.data.model.NaverApi

interface NaverRemoteDataSource {
    fun getMovieData(title: String, onResponse: (NaverApi) -> Unit, onFailure: (Throwable) -> Unit)
}