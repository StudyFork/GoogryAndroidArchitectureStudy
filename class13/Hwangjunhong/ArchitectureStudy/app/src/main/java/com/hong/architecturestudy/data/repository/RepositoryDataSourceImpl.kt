package com.hong.architecturestudy.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.source.local.LocalDataSource
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import com.hong.architecturestudy.data.source.remote.RemoteDataSource

class RepositoryDataSourceImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RepositoryDataSource {

    override fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        remoteDataSource.getMovieList(query, onSuccess, onFailure)
    }

    override fun saveData(keyword: String, context: Context) {
        localDataSource.saveData(keyword, context)
    }

    override fun loadData(
        context: Context
    ): LiveData<List<MovieInfo>> {
        return localDataSource.loadData(context)
    }


}