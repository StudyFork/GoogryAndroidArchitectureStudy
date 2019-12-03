package com.ironelder.androidarchitecture.data.source

import com.ironelder.androidarchitecture.data.TotalModel

interface SearchDataSource{
    fun getDataForSearch(
        type: String,
        query: String,
        success: (result: TotalModel) -> Unit,
        fail: (msg: String) -> Unit
    )
}