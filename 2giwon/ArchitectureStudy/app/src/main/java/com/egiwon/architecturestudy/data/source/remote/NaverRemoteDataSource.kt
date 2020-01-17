package com.egiwon.architecturestudy.data.source.remote

import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.remote.response.ContentResponse
import io.reactivex.Single

class NaverRemoteDataSource(
    private val contentsService: ContentsService
) : NaverDataSource.Remote {

    override fun getContents(type: String, query: String): Single<ContentResponse> =
        contentsService.getContentsInfo(type = type, query = query)

}