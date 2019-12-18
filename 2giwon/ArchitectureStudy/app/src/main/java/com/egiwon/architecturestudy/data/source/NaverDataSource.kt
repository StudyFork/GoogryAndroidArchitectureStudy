package com.egiwon.architecturestudy.data.source

import com.egiwon.architecturestudy.data.source.remote.response.ContentResponse
import io.reactivex.Single

interface NaverDataSource {

    interface Remote {
        fun getContents(type: String, query: String): Single<ContentResponse>
    }

    interface Local {
        fun getCacheContents(type: String): Single<ContentResponse>

        fun saveContents(type: String, query: String, response: ContentResponse)
    }


}