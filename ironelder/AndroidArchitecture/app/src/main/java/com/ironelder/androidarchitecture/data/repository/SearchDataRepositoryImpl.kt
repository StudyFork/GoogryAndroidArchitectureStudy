package com.ironelder.androidarchitecture.data.repository

import com.google.gson.Gson
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.dao.SearchResultDao
import com.ironelder.androidarchitecture.data.source.local.LocalSearchDataSourceImpl
import com.ironelder.androidarchitecture.data.source.remote.RemoteSearchDataSourceImpl
import io.reactivex.Single

object SearchDataRepositoryImpl :
    SearchDataRepository {

    override fun getRemoteSearchData(
        type: String,
        query: String,
        searchResultDao: SearchResultDao
    ): Single<TotalModel> {
        return RemoteSearchDataSourceImpl.getRemoteSearchData(type, query)
            .doAfterSuccess { result: TotalModel? ->
                LocalSearchDataSourceImpl.setLocalSearchData(
                    searchResultDao, SearchResult(
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
        return LocalSearchDataSourceImpl.getLocalSearchData(searchResultDao, type)
    }

}