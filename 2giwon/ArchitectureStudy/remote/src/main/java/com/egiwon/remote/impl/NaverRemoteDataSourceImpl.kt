package com.egiwon.remote.impl

import com.egiwon.data.NaverRemoteDataSource
import com.egiwon.data.model.ContentEntity
import com.egiwon.remote.response.mapToContentEntity
import com.egiwon.remote.service.ContentService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NaverRemoteDataSourceImpl(
    private val contentService: ContentService
) : NaverRemoteDataSource {

    override fun getRemoteContents(type: String, query: String): Single<ContentEntity> =
        contentService.getContentInfo(type, query)
            .map { response ->
                response.query = query
                response.mapToContentEntity()
            }.subscribeOn(Schedulers.io())


}