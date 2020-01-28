package com.example.architecturestudy.data.repository

import android.util.Log
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
                    if(it.isNotEmpty()) {
                        naverSearchLocalDataSource.deleteMovie(it.map { it.toEntity() })
                        naverSearchLocalDataSource.saveMovieItems(it.map { it.toEntity() })
                        success(it)
                        Log.e("isConnected", "$it")
                    }
                },
                fail = fail
            )
        } else {
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
    }

    override fun getLastMovie(
        success: (List<MovieItem>) -> Unit,
        fail: (Throwable) -> Unit) {
            naverSearchLocalDataSource.getMovieItems(
                success = {
                    if(it.isNotEmpty()) {
                        success(it.map { it.toItem() })
                    } else {
                        fail(Throwable("empty data"))
                    }
                },
                fail = fail
            )
    }

    override fun getBlog(
        isNetwork: Boolean,
        keyword: String,
        success: (List<BlogItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        if(isNetwork) {
            naverSearchRemoteDataSource.getBlog(
                keyword = keyword,
                success = {
                    if(it.isNotEmpty()) {
                        naverSearchLocalDataSource.deleteBlog(it.map { it.toEntity() })
                        naverSearchLocalDataSource.saveBlogItems(it.map { it.toEntity() })
                        success(it)
                    }
                },
                fail = fail
            )
        } else {
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
    }

    override fun getLastBlog(success: (List<BlogItem>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchLocalDataSource.getBlogItems(
            success = {
                if(it.isNotEmpty()) {
                    success(it.map { it.toItem() })
                } else {
                    fail(Throwable("empty data"))
                }
            },
            fail = fail
        )
    }

    override fun getKin(
        isNetwork: Boolean,
        keyword: String,
        success: (List<KinItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        if(isNetwork) {
            naverSearchRemoteDataSource.getKin(
                keyword = keyword,
                success = {
                    if(it.isNotEmpty()) {
                        naverSearchLocalDataSource.deleteKin(it.map { it.toEntity() })
                        naverSearchLocalDataSource.saveKinItems(it.map { it.toEntity() })
                        success(it)
                    }
                },
                fail = fail
            )
        } else {
            naverSearchLocalDataSource.getKiItems(
                 success = {
                     if(it.isNotEmpty()) {
                         success(it.map { it.toItem() })
                     } else {
                         fail(Throwable("empty data"))
                     }
                 },
                fail = fail
            )
        }
    }

    override fun getLastKin(
        success: (List<KinItem>) -> Unit,
        fail: (Throwable) -> Unit) {
        naverSearchLocalDataSource.getKiItems(
            success = {
                if(it.isNotEmpty()) {
                    success(it.map { it.toItem() })
                } else {
                    fail(Throwable("empty data"))
                }
            },
            fail = fail
        )
    }

    override fun getImage(
        isNetwork: Boolean,
        keyword: String,
        success: (List<ImageItem>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        if(isNetwork) {
            naverSearchRemoteDataSource.getImage(
                keyword = keyword,
                success = {
                    if(it.isNotEmpty()) {
                        naverSearchLocalDataSource.deleteImage(it.map { it.toEntity() })
                        naverSearchLocalDataSource.saveImageItems(it.map { it.toEntity() })
                        success(it)
                    }

                },
                fail = fail
            )
        } else {
            naverSearchLocalDataSource.getImageItems(
                success = {
                    if(it.isNotEmpty()) {
                        success(it.map { it.toItem() })
                    } else {
                        fail(Throwable("empty data"))
                    }
                },
                fail = fail
            )
        }

    }

    override fun getLastImage(
        success: (List<ImageItem>) -> Unit,
        fail: (Throwable) -> Unit) {
        naverSearchLocalDataSource.getImageItems(
            success = {
                if(it.isNotEmpty()) {
                    success(it.map { it.toItem() })
                } else {
                    fail(Throwable("empty data"))
                }
            },
            fail = fail
        )
    }
}