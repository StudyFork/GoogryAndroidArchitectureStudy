package com.example.architecturestudy.data.repository

import com.example.architecturestudy.network.response.BlogData
import com.example.architecturestudy.network.response.ImageData
import com.example.architecturestudy.network.response.KinData
import com.example.architecturestudy.network.response.MovieData
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource
) : NaverSearchRepository {


    override fun getMovie(keyword: String, success: (MovieData) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getBlog(keyword: String, success: (BlogData) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getKin(keyword: String, success: (KinData) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getKin(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getImage(keyword: String, success: (ImageData) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getImage(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }
}