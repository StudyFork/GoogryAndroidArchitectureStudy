package com.buddman1208.architecturestudy.data

import com.buddman1208.architecturestudy.models.CommonResponse
import com.buddman1208.architecturestudy.utils.NetworkManager
import io.reactivex.Single

object NaverRemoteDataSourceImpl : NaverData {

    override fun searchByType(searchType: String, query: String): Single<CommonResponse> {
        return NetworkManager.searchByType(searchType, query)
    }

}