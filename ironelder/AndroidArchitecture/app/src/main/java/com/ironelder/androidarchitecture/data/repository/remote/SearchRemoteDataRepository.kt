package com.ironelder.androidarchitecture.data.repository.remote

import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

interface SearchRemoteDataRepository {

    fun getDataForSearch(
        type: String,
        query: String?
    ):Single<TotalModel>

}