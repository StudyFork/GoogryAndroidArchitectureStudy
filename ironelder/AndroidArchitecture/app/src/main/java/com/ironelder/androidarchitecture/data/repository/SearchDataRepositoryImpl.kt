package com.ironelder.androidarchitecture.data.repository

import com.google.gson.Gson
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.dao.SearchResultDao
import com.ironelder.androidarchitecture.data.source.local.LocalSearchDataSource
import com.ironelder.androidarchitecture.data.source.remote.RemoteSearchDataSource
import com.ironelder.androidarchitecture.data.source.remote.RemoteSearchDataSourceImpl
import io.reactivex.Single

class SearchDataRepositoryImpl(
    private val localSearchDataSourceImpl: LocalSearchDataSource,
    private val remoteSearchDataSourceImpl: RemoteSearchDataSource
) :
    SearchDataRepository {

    override fun getRemoteSearchData(
        type: String,
        query: String,
        searchResultDao: SearchResultDao
    ): Single<TotalModel> {
        return remoteSearchDataSourceImpl.getRemoteSearchData(type, query)
            .doAfterSuccess { result: TotalModel? ->
                localSearchDataSourceImpl.setLocalSearchData(
                    SearchResult(
                        null,
                        type,
                        query,
                        Gson().toJson(result?.items)
                    )
                )
            }
    }

    override fun getLocalSearchData(
        type: String,
        searchResultDao: SearchResultDao
    ): Single<SearchResult>? {
        return localSearchDataSourceImpl.getLocalSearchData(type)
    }

}