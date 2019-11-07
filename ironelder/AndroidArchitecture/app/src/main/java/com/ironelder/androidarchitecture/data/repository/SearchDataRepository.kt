package com.ironelder.androidarchitecture.data.repository

import com.ironelder.androidarchitecture.data.TotalModel
import retrofit2.Call

interface SearchDataRepository {
    fun getDataForSearch(type: String, query: String): Call<TotalModel>
}