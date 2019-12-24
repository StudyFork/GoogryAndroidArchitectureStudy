package com.ironelder.androidarchitecture.data.repository

import com.google.gson.Gson
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import com.ironelder.androidarchitecture.data.source.remote.RemoteSearchDataSourceImpl
import io.reactivex.Single

object SearchDataRepositoryImpl :
    SearchDataRepository {

    override fun getRemoteSearchData(
        type: String,
        query: String?,
        database: SearchResultDatabase?
    ): Single<TotalModel> {
        return RemoteSearchDataSourceImpl.getRemoteSearchData(type, query)
            .doAfterSuccess { result: TotalModel? ->
                database?.SearchResultDao()
                    ?.insertSearchResult(
                        SearchResult(
                            null,
                            type,
                            query ?: "",
                            Gson().toJson(result?.items)
                        )
                    )
            }
    }

    override fun getLocalSearchData(
        type: String,
        database: SearchResultDatabase?
    ): Single<SearchResult>? {
        return database?.SearchResultDao()?.getLastSearchResult(type)
    }

}