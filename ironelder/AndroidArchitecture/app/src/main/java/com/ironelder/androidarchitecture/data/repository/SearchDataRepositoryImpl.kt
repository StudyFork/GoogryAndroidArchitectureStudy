package com.ironelder.androidarchitecture.data.repository

import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.source.SearchDataSourceImpl
import io.reactivex.Single

object SearchDataRepositoryImpl : SearchDataRepository {

    override fun getDataForSearch(
        type: String,
        query: String?,
        observable: (observable: Single<TotalModel>) -> Unit
    ) {
        observable(SearchDataSourceImpl.getDataForSearchWithAdapter(type, query))
    }

}