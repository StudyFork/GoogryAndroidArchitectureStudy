package com.example.aas.data.repository

import com.example.aas.data.model.ApiResult
import com.example.aas.data.source.local.LocalDataSource
import com.example.aas.data.source.local.LocalDataSourceImpl
import com.example.aas.data.source.remote.RemoteDataSource
import com.example.aas.data.source.remote.RemoteDataSourceImpl
import io.reactivex.Completable
import io.reactivex.Single

object MovieSearchRepositoryImpl : MovieSearchRepository {
    private val localDataSource: LocalDataSource = LocalDataSourceImpl()
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    override fun getMovies(query: String): Single<Pair<ApiResult, Completable>> {
        return remoteDataSource.getMovies(query)
            .flatMap {
                Single.just(Pair(it, localDataSource.saveQuery(query)))
            }
    }

    override fun getSavedQueries(): Single<List<String>> = localDataSource.getSavedQuery()
}