package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.local.Entity.MovieEntity
import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource
) : NaverSearchRepository {

    override fun getMovie(
        isNetwork: Boolean,
        keyword: String,
        success: (List<MovieItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        if (isNetwork) {
            naverSearchRemoteDataSource.getMovie(
                keyword = keyword,
                success = {
                    // 로컬 저장
                    naverSearchLocalDataSource.saveSearchItems(items = it.map { it.toEntity() })
                    success(it)
                },
                fail = fail
            )
        } else {
            naverSearchLocalDataSource.getSearchItems<MovieEntity>(
                keyword = keyword,
                success = {
                    if (it.isNotEmpty()) {
                        success(it.map { it.toItem() })
                        return@getSearchItems
                    }
                    fail(Throwable("empty list"))
                },
                fail = fail
            )
        }
    }

    override fun getBlog(
        keyword: String,
        success: (List<BlogItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getKin(
        keyword: String,
        success: (List<KinItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDataSource.getKin(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getImage(
        keyword: String,
        success: (List<ImageItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDataSource.getImage(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }
}