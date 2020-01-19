package com.example.archstudy.data.repository

import android.content.Context
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.MovieDataResponse
import com.example.archstudy.data.source.local.*
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSourceImpl
import retrofit2.Call

class NaverQueryRepositoryImpl(
    private val naverQueryLocalDataSource: NaverQueryLocalDataSourceImpl,
    private val naverQueryRemoteDataSource: NaverQueryRemoteDataSourceImpl
) : NaverQueryRepository {

    private lateinit var localQuery: String

    override fun requestRemoteData(query: String): Call<MovieDataResponse> {
        return naverQueryRemoteDataSource.getMovie(query)
    }

    //TODO 비동기
    override fun requestLocalData(
        query: String
    ): MutableList<MovieData>? {
        return naverQueryLocalDataSource.requestLocalData(query)
    }

    //TODO 비동기
    override fun insertLocalData(query: String, data: List<MovieData>, context: Context) {
        this.localQuery = query
        naverQueryLocalDataSource.insertLocalData(query, data.toMutableList())
    }


}