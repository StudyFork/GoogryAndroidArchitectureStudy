package com.egiwon.architecturestudy.data.source

import com.egiwon.architecturestudy.data.Content
import io.reactivex.disposables.Disposable

interface NaverDataSource {
    fun getContents(
        type: String,
        query: String,
        onSuccess: (List<Content.Item>) -> Unit,
        onFailure: (Throwable) -> Unit
    ): Disposable
}