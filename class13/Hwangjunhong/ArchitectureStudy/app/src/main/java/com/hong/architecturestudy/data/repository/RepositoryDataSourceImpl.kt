package com.hong.architecturestudy.data.repository

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
        remoteDataSource.getMovieList(
            query,
            {
                onSuccess(it)
                saveResentSearchQuery(query)
            }, onFailure
        )
    }

    override fun saveResentSearchQuery(keyword: String) =
        localDataSource.saveResentSearchQuery(keyword)


    override fun loadResentSearchQuery(): List<MovieInfo> =
        localDataSource.loadResentSearchQuery()


}