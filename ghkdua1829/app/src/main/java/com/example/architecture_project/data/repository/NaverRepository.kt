package com.example.architecture_project.data.repository

import com.example.architecture_project.data.datasource.remote.NaverRemoteDataSource
import com.example.architecture_project.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.architecture_project.data.model.NaverApi

class NaverRepository {
    var NaverRemoteDataSourece:NaverRemoteDataSource = NaverRemoteDataSourceImpl()

    fun getMovieData(title:String,success:(NaverApi)->Unit,fail:(Throwable)->Unit){
        NaverRemoteDataSourece.getMovieData(title,success,fail)
    }
}