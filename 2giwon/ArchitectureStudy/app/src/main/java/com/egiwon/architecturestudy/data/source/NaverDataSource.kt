package com.egiwon.architecturestudy.data.source

import com.egiwon.architecturestudy.data.Content
import io.reactivex.Single

interface NaverDataSource {
    fun getContents(type: String, query: String): Single<Content>
}