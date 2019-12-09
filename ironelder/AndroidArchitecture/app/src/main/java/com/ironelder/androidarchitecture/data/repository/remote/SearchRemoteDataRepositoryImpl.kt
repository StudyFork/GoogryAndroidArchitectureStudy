package com.ironelder.androidarchitecture.data.repository.remote

import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.source.remote.SearchRemoteDataSourceImpl
import io.reactivex.Single

object SearchRemoteDataRepositoryImpl :
    SearchRemoteDataRepository {

    override fun getDataForSearch(
        type: String,
        query: String?
    ): Single<TotalModel> {
        return SearchRemoteDataSourceImpl.getDataForSearch(type, query)
    }

}