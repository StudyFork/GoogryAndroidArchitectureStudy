package com.egiwon.local.source.impl

import com.egiwon.data.NaverLocalDataSource
import com.egiwon.data.model.ContentEntity
import com.egiwon.local.ContentDao
import com.egiwon.local.model.Content
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NaverLocalDataSourceImpl(
    private val contentDao: ContentDao
) : NaverLocalDataSource {

    override fun getCacheContents(type: String): Single<ContentEntity> =
        contentDao.getContentCache(type)
            .onErrorReturn { Content.empty(type, "") }
            .map { ContentEntity(it.query, it.list) }
            .toSingle()
            .subscribeOn(Schedulers.io())

    override fun getContentQueries(type: String): Single<List<String>> =
        contentDao.getContentQuery(type)
            .onErrorReturn { emptyList() }
            .toSingle()
            .subscribeOn(Schedulers.io())

    override fun getLocalContents(type: String, query: String): Single<ContentEntity> =
        contentDao.getContents(type, query)
            .onErrorReturn { Content.empty(type, query) }
            .map { ContentEntity(it.query, it.list) }
            .toSingle()
            .subscribeOn(Schedulers.io())

    override fun saveContents(type: String, query: String, response: ContentEntity) =
        contentDao.insertContent(
            Content(
                System.currentTimeMillis(),
                response.contentItems,
                type,
                query
            )
        )
}