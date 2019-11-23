package com.egiwon.architecturestudy.data.source

import com.egiwon.architecturestudy.data.Content

interface NaverDataSource {

    fun getContents(
        type: String,
        query: String,
        onSuccess: (resultList: List<Content.Item>) -> Unit,
        onFailure: (throwable: Throwable) -> Unit
    )
}