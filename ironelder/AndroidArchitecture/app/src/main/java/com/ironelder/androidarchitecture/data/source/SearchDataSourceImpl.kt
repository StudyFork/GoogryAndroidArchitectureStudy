package com.ironelder.androidarchitecture.data.source

import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

object SearchDataSourceImpl : SearchDataSource {

    override fun getDataForSearchWithAdapter(type: String, query: String?): Single<TotalModel> {
        return RetrofitForNaver.searchApi.requestSearchForNaverWithAdapter(type, query)
    }

}