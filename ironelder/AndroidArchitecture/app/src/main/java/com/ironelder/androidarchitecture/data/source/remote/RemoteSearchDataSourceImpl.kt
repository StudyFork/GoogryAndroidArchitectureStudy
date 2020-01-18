package com.ironelder.androidarchitecture.data.source.remote

import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

class RemoteSearchDataSourceImpl(private val retrofitServie:RetrofitForNaver) :
    RemoteSearchDataSource {

    override fun getRemoteSearchData(type: String, query: String?): Single<TotalModel> {
        return retrofitServie.searchApi.requestSearchForNaverWithAdapter(type, query)
    }

}