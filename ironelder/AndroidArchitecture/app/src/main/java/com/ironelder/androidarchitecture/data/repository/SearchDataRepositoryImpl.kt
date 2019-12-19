package com.ironelder.androidarchitecture.data.repository

import android.content.Context
import com.google.gson.Gson
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.source.local.LocalSearchDataSourceImpl
import com.ironelder.androidarchitecture.data.source.remote.RemoteSearchDataSourceImpl
import io.reactivex.Single

object SearchDataRepositoryImpl :
    SearchDataRepository {

    override fun getRemoteSearchData(
        context: Context,
        type: String,
        query: String?
    ): Single<TotalModel> {
        return RemoteSearchDataSourceImpl.getRemoteSearchData(type, query)
            .doAfterSuccess { result: TotalModel? ->
                setLocalCache(
                    context,
                    type,
                    query,
                    result
                )
            }
    }

    override fun getLocalSearchData(context: Context, type: String): Single<SearchResult>? {
        return LocalSearchDataSourceImpl.getLocalSearchData(context, type)
    }

    private fun setLocalCache(
        context: Context,
        type: String,
        searchWord: String?,
        result: TotalModel?
    ) {
        if (result != null) {
            LocalSearchDataSourceImpl.setLocalSearchData(
                context, SearchResult(
                    null,
                    type,
                    searchWord?:"",
                    Gson().toJson(result.items)
                )
            )
        }
    }

}