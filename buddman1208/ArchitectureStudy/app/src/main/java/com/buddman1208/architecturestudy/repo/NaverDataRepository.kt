package com.buddman1208.architecturestudy.repo

import com.buddman1208.architecturestudy.models.CommonResponse

interface NaverDataRepository {

    fun searchByTypeFromNaver(
        searchType: String,
        query: String,
        onSuccess: (result: CommonResponse) -> Unit,
        onFailure: (error: Throwable) -> Unit
    )
}