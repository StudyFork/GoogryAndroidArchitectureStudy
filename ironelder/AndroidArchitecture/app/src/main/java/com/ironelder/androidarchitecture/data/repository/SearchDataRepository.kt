package com.ironelder.androidarchitecture.data.repository

import retrofit2.Call

interface SearchDataRepository<T> {
    fun getDataForSearch(type: String, query: String): Call<T>
}