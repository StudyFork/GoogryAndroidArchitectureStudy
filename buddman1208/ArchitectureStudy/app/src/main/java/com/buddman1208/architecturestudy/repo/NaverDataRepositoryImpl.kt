package com.buddman1208.architecturestudy.repo

import com.buddman1208.architecturestudy.data.NaverRemoteDataSourceImpl
import com.buddman1208.architecturestudy.models.CommonResponse
import com.buddman1208.architecturestudy.utils.subscribeOnIO
import io.reactivex.Single

object NaverDataRepositoryImpl : NaverDataRepository {

    override fun searchByTypeFromNaver(
        searchType: String,
        query: String
    ) : Single<CommonResponse> {
        return NaverRemoteDataSourceImpl
            .searchByType(
                searchType = searchType,
                query = query
            )
            .subscribeOnIO()
    }

}