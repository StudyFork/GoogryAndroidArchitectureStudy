package com.example.aas.data.repository

import android.content.Context
import com.example.aas.data.model.ApiResult
import com.example.aas.data.source.local.LocalDataSource
import com.example.aas.data.source.local.LocalDataSourceImpl
import com.example.aas.data.source.remote.RemoteDataSource
import com.example.aas.data.source.remote.RemoteDataSourceImpl
import io.reactivex.Single

object MovieSearchRepositoryImpl : MovieSearchRepository {
    private val localDataSource: LocalDataSource = LocalDataSourceImpl()
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    override fun getMovies(query: String, context: Context): Single<ApiResult> {
        localDataSource.saveQuery(query, context)
        return remoteDataSource.getMovies(query)
    }

    override fun getSavedQueries(context: Context): List<String> =
        localDataSource.getSavedQuery(context)

}