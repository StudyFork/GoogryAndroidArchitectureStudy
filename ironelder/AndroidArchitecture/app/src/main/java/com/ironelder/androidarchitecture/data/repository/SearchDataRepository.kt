package com.ironelder.androidarchitecture.data.repository

import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

interface SearchDataRepository {

    fun getDataForSearch(
        type: String,
        query: String?
    ):Single<TotalModel>

}