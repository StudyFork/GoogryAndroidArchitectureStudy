package com.buddman1208.architecturestudy.data

import com.buddman1208.architecturestudy.models.CommonResponse
import io.reactivex.Single

interface NaverRemoteDataSource {

    fun searchByType(searchType: String, query: String): Single<CommonResponse>

}