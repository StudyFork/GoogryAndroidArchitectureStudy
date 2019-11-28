package com.ironelder.androidarchitecture.data.repository

import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single
import retrofit2.Call

interface SearchDataRepository {
    fun getDataForSearch(type: String, query: String): Call<TotalModel>
    fun getDataForSearchWithAdapter(type: String, query: String?): Single<TotalModel>
}