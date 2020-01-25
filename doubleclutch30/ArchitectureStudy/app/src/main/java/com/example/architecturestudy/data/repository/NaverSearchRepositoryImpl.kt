package com.example.architecturestudy.data.repository

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
                    naverSearchLocalDataSource.saveMovieItems(items = it.map { it.toEntity() })
                    success(it)
                },
                fail = fail
            )
        } else {
            naverSearchLocalDataSource.getMovieItems(
                success = {
                    if (it.isNotEmpty()) {
                        naverSearchLocalDataSource.deleteMovie(it)
                        success(it.map { it.toItem() })
                        return@getMovieItems
                    }
                    fail(Throwable("empty list"))
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
                        naverSearchLocalDataSource.deleteMovie(it)
                        success(it.map { it.toItem() })
                        return@getMovieItems
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
                    naverSearchLocalDataSource.saveBlogItems(items = it.map { it.toEntity() })
                    success(it)
                },
                fail = fail
            )
        } else {
            naverSearchLocalDataSource.getBlogItems(
                success = {
                    if (it.isNotEmpty()) {
                        naverSearchLocalDataSource.deleteBlog(it)
                        success(it.map { it.toItem() })
                        return@getBlogItems
                    }
                    fail(Throwable("empty list"))
                },
                fail = fail
            )
        }
    }

    override fun getLastBlog(success: (List<BlogItem>) -> Unit, fail: (Throwable) -> Unit) {
        naverSearchLocalDataSource.getBlogItems(
            success = {
                if(it.isNotEmpty()) {
                    naverSearchLocalDataSource.deleteBlog(it)
                    success(it.map { it.toItem() })
                    return@getBlogItems
                } else {
                    fail(Throwable("empty list"))
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
                    naverSearchLocalDataSource.saveKinItems(items = it.map { it.toEntity() })
                    success(it)
                },
                fail = fail
            )
        } else {
            naverSearchLocalDataSource.getKiItems(
                 success = {
                     if(it.isNotEmpty()) {
                        naverSearchLocalDataSource.deleteKin(it)
                         success(it.map { it.toItem() })
                         return@getKiItems
                     } else {
                         fail(Throwable("empty List"))
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
                    naverSearchLocalDataSource.deleteKin(it)
                    success(it.map { it.toItem() })
                    return@getKiItems
                } else {
                    fail(Throwable("empty list"))
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
                    naverSearchLocalDataSource.saveImageItems(it.map { it.toEntity() })
                    success(it)
                },
                fail = fail
            )
        } else {
            naverSearchLocalDataSource.getImageItems(
                success = {
                    if(it.isNotEmpty()) {
                        naverSearchLocalDataSource.deleteImage(it)
                        success(it.map { it.toItem() })
                        return@getImageItems
                    } else {
                        fail(Throwable("empty list"))
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
                    naverSearchLocalDataSource.deleteImage(it)
                    success(it.map { it.toItem() })
                    return@getImageItems
                } else {
                    fail(Throwable("empty list"))
                }
            },
            fail = fail
        )
    }
}