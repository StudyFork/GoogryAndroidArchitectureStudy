package com.egiwon.architecturestudy.data.source.remote

import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.remote.response.ContentResponse
import io.reactivex.Single

class NaverRemoteDataSource(
    private val contentService: ContentService
) : NaverDataSource.Remote {

    override fun getRemoteContents(type: String, query: String): Single<ContentResponse> =
        contentService.getContentInfo(type = type, query = query)

}