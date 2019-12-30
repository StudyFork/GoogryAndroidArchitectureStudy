package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.model.*
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import io.reactivex.Single

class NaverSearchRepositoryImpl : NaverSearchRepository {
    override val naverSearchRemoteDataSource: NaverSearchRemoteDataSource by lazy {
        NaverSearchRemoteDataSourceImpl()
    }

    override fun getMovie(
        keyword: String
    ) : Single<ResponseMovie> =
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword
        )


    override fun getImage(
        keyword: String
    ) : Single<ResponseImage> =
        naverSearchRemoteDataSource.getImage(
            keyword = keyword
        )

    override fun getBlog(
        keyword: String
    ) : Single<ResponseBlog> =
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword
        )

    override fun getKin(
        keyword: String
    ) : Single<ResponseKin> =
        naverSearchRemoteDataSource.getKin(
            keyword = keyword
        )

}