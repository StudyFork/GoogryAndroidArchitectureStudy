package com.egiwon.architecturestudy.data.source.remote

import com.egiwon.architecturestudy.data.Content

interface NaverRemoteDataSource {
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