package com.egiwon.architecturestudy.data.source

import com.egiwon.architecturestudy.data.Content

interface NaverDataSource {

    interface Callback {
        fun onSuccess(list: List<Content.Item>)
        fun onFailure(throwable: Throwable)
    }

    fun getContents(
        type: String,
        query: String,
        callback: Callback
    )
}