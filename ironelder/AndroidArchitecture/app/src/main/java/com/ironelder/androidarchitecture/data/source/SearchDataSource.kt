package com.ironelder.androidarchitecture.data.source

import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

interface SearchDataSource{

    fun getDataForSearch(type: String, query: String?): Single<TotalModel>

}