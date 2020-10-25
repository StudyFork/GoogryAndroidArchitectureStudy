package com.hong.architecturestudy.data.repository

import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.source.local.LocalDataSource
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import com.hong.architecturestudy.data.source.remote.RemoteDataSource
import javax.inject.Inject

class RepositoryDataSourceImpl @Inject constructor(
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
                saveRecentSearchQuery(query)
            }, onFailure
        )
    }

    override fun saveRecentSearchQuery(keyword: String) =
        localDataSource.saveRecentSearchQuery(keyword)


    override fun loadRecentSearchQuery(): List<MovieInfo> =
        localDataSource.loadRecentSearchQuery()


}