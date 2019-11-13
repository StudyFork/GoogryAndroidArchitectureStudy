package com.example.androidstudy.model.repository

import com.example.androidstudy.api.data.TotalModel

interface NaverDataRepository {
    fun getNaverSearchData(
        type: String,
        query: String,
        success: (result: TotalModel) -> Unit,
        fail: (msg: String) -> Unit
    )
}