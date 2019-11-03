package com.ironelder.androidarchitecture.data.repository

import retrofit2.Call

interface ISearchDataRepo<T> {
    fun getDataForSearch(type:String, query:String):Call<T>
}