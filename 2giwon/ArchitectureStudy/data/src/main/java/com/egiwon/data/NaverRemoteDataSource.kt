package com.egiwon.data

import com.egiwon.data.model.ContentEntity
import io.reactivex.Single

interface NaverRemoteDataSource {
    fun getRemoteContents(type: String, query: String): Single<ContentEntity>
}