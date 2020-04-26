package com.byiryu.data.source.remote

import com.byiryu.data.model.Response
import io.reactivex.Single

interface RemoteDataSource {
    fun getMoveList(query: String): Single<Response>
}