package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource
) : NaverSearchRepository {

    override fun getMovie(keyword: String, success: (List<MovieItem>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getBlog(keyword: String, success: (List<BlogItem>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getKin(keyword: String, success: (List<KinItem>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getKin(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getImage(keyword: String, success: (List<ImageItem>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchRemoteDataSource.getImage(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }
}