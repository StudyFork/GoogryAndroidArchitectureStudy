package com.example.androidstudy.model.data

import com.example.androidstudy.api.data.TotalModel
import retrofit2.Call

interface INaverData {
    fun callAPiNaverSearch(type: String, query: String): Call<TotalModel>
}