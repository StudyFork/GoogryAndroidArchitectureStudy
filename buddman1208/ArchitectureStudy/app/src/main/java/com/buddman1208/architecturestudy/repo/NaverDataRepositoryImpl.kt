package com.buddman1208.architecturestudy.repo

import com.buddman1208.architecturestudy.data.NaverDataImpl
import com.buddman1208.architecturestudy.models.CommonResponse
import com.buddman1208.architecturestudy.utils.subscribeOnIO

object NaverDataRepositoryImpl : NaverDataRepository {

    override fun searchByTypeFromNaver(
        searchType: String,
        query: String,
        onSuccess: (result: CommonResponse) -> Unit,
        onFailure: (error: Throwable) -> Unit
    ) {
        NaverDataImpl
            .searchByType(
                searchType = searchType,
                query = query
            )
            .subscribeOnIO()
            .subscribe(
                { onSuccess(it) },
                { onFailure(it) }
            )
    }

}