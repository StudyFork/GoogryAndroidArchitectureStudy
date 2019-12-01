package com.buddman1208.architecturestudy.repo

import com.buddman1208.architecturestudy.models.CommonResponse
import io.reactivex.Single

interface NaverDataRepository {

    fun searchByTypeFromNaver(
        searchType: String,
        query: String
    ) : Single<CommonResponse>
}