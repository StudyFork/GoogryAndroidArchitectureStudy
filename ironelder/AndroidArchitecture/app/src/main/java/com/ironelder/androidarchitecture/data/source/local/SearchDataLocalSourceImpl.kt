package com.ironelder.androidarchitecture.data.source.local

import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.source.SearchDataSource
import io.reactivex.Single

object SearchDataLocalSourceImpl : SearchDataSource {
    override fun getDataForSearch(type: String, query: String?): Single<TotalModel> {

        return RetrofitForNaver.searchApi.requestSearchForNaverWithAdapter(type, query)    }
}