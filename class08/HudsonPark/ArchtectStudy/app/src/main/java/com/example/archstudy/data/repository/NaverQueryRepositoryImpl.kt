package com.example.archstudy.data.repository

import com.example.archstudy.MovieData
import com.example.archstudy.data.source.local.NaverQueryLocalDataSourceImpl
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSourceImpl
import retrofit2.Call

class NaverQueryRepositoryImpl
    : NaverQueryRepository {

    private lateinit var naverQueryLocalDataSource: NaverQueryLocalDataSourceImpl
    private lateinit var naverQueryRemoteDataSource: NaverQueryRemoteDataSourceImpl

    override fun requestRemoteData(query: String): Call<MovieData> {
        naverQueryRemoteDataSource = NaverQueryRemoteDataSourceImpl()
        return naverQueryRemoteDataSource.getMovie(query)
    }

    override fun requestLocalData(query: String) : MovieData{
        return MovieData(0, mutableListOf(),0,0)
    }




}

