package com.cnm.homework.data.source.remote

import com.cnm.homework.network.model.NaverResponse
import io.reactivex.Single

interface NaverQueryRemoteDataSource {
    fun getNaverMovie(query: String): Single<NaverResponse>

}