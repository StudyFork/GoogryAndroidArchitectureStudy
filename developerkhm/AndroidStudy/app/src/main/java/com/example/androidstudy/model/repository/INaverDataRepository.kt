package com.example.androidstudy.model.repository

import com.example.androidstudy.R

interface INaverDataRepository {
    fun getNaverSearchData(
        type: String,
        query: String,
        success: () -> Unit,
        fail: (msg: String) -> Unit
    )
}