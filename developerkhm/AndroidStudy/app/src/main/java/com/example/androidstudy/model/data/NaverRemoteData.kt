package com.example.androidstudy.model.data

import com.example.androidstudy.api.data.TotalModel
import retrofit2.Call

interface NaverRemoteData {
    fun callAPiNaverSearch(type: String, query: String): Call<TotalModel>
}