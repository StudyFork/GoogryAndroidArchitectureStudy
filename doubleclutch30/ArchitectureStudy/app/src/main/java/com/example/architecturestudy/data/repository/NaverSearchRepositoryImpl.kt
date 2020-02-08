package com.example.architecturestudy.data.repository

import android.util.Log
import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.example.architecturestudy.network.response.BlogData
import com.example.architecturestudy.network.response.ImageData
import com.example.architecturestudy.network.response.KinData
import com.example.architecturestudy.network.response.MovieData
import io.reactivex.Observable
import io.reactivex.Single
import kotlin.math.sin

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource
) : NaverSearchRepository {

    override fun getMovie(
        keyword: String
    ): Single<MovieData> =
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword
    )
        .flatMap {
            //                naverSearchLocalDataSource.deleteMovie()
            naverSearchLocalDataSource.saveMovieItems(it.items.map { it.toEntity() })
            return@flatMap Single.just(it)
        }


    override fun getLastMovie(
        success: (List<MovieItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchLocalDataSource.getMovieItems(
            success = {
                if (it.isNotEmpty()) {
                    success(it.map { it.toItem() })
                } else {
                    fail(Throwable("empty data"))
                }
            },
            fail = fail
        )
    }

    override fun getBlog(
        keyword: String
    ): Single<BlogData> =
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword
        )
            .flatMap {
                naverSearchLocalDataSource.saveBlogItems(it.items.map { it.toEntity() })
                return@flatMap Single.just(it)
            }


    override fun getLastBlog(success: (List<BlogItem>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchLocalDataSource.getBlogItems(
            success = {
                if (it.isNotEmpty()) {
                    success(it.map { it.toItem() })
                } else {
                    fail(Throwable("empty data"))
                }
            },
            fail = fail
        )
    }

    override fun getKin(
        keyword: String
    ): Single<KinData> =
        naverSearchRemoteDataSource.getKin(
            keyword = keyword
        )
            .flatMap {
                naverSearchLocalDataSource.deleteKin()
                naverSearchLocalDataSource.saveKinItems(it.items.map { it.toEntity() })
                return@flatMap Single.just(it)
            }

    override fun getLastKin(
        success: (List<KinItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchLocalDataSource.getKiItems(
            success = {
                if (it.isNotEmpty()) {
                    success(it.map { it.toItem() })
                } else {
                    fail(Throwable("empty data"))
                }
            },
            fail = fail
        )
    }

    override fun getImage(
        keyword: String
    ): Single<ImageData> =
            naverSearchRemoteDataSource.getImage(
                keyword = keyword
            )
                .flatMap {
                    naverSearchLocalDataSource.deleteImage()
                    naverSearchLocalDataSource.saveImageItems(it.items.map { it.toEntity() })
                    return@flatMap Single.just(it)
                }


    override fun getLastImage(
        success: (List<ImageItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchLocalDataSource.getImageItems(
            success = {
                if (it.isNotEmpty()) {
                    success(it.map { it.toItem() })
                } else {
                    fail(Throwable("empty data"))
                }
            },
            fail = fail
        )
    }
}