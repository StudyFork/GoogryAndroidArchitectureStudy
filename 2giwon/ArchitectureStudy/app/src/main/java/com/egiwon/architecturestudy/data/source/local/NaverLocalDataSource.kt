package com.egiwon.architecturestudy.data.source.local

import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.local.model.Content
import com.egiwon.architecturestudy.data.source.remote.response.ContentResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NaverLocalDataSource(
    private val contentDao: ContentDao
) : NaverDataSource.Local {

    override fun getCacheContents(type: String): Single<ContentResponse> =
        contentDao.getContentCache(type)
            .onErrorReturn { Content.empty(type, "") }
            .map { ContentResponse(it.query, it.list) }
            .toSingle()
            .subscribeOn(Schedulers.io())

    override fun saveContents(type: String, query: String, response: ContentResponse) =
        contentDao.insertContent(
            Content(
                System.currentTimeMillis(),
                response.contentItems,
                type,
                query
            )
        )

    companion object {
        private var instance: NaverLocalDataSource? = null

        fun getInstance(contentDao: ContentDao) = instance ?: NaverLocalDataSource(contentDao)
            .apply { instance = this }

    }

}