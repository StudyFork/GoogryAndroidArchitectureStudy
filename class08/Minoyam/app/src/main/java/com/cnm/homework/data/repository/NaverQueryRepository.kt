package com.cnm.homework.data.repository

import com.cnm.homework.data.model.NaverResponse
import io.reactivex.Single

interface NaverQueryRepository {
    fun getNaverMovie(query: String): Single<NaverResponse>
}