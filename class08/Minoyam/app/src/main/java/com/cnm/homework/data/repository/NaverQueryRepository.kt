package com.cnm.homework.data.repository

import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.local.db.LocalEntity
import io.reactivex.Single

interface NaverQueryRepository {
    fun getNaverMovie(query: String): Single<NaverResponse>

    fun saveCacheMovie(localEntity: LocalEntity): Single<Unit>

    fun loadLocal(): List<NaverResponse.Item>


}