package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.BlogItems
import com.example.architecturestudy.data.model.ImageItems
import com.example.architecturestudy.data.model.KinItems
import com.example.architecturestudy.data.model.MovieItems
import com.example.architecturestudy.network.response.MovieData
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource
) : NaverSearchRepository {

    override fun getMovie(keyword: String, success: (List<MovieItems>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getBlog(keyword: String, success: (List<BlogItems>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getKin(keyword: String, success: (List<KinItems>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getKin(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getImage(keyword: String, success: (List<ImageItems>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getImage(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }
}