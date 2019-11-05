package com.example.androidstudy.model.repository

object INaverDataRepositoryImpl : INaverDataRepository{
    override fun getNaverSearchData(
        type: String,
        query: String,
        success: () -> Unit,
        fail: (msg: String) -> Unit
    ) {
        
    }

}