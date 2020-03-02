package com.jay.repository.repository

import com.jay.local.NaverSearchLocalDataSource
import com.jay.remote.NaverSearchRemoteDataSource
import com.jay.repository.mapper.*
import com.jay.repository.model.*
import io.reactivex.Completable
import io.reactivex.Single

internal class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource
) : NaverSearchRepository {

    override fun getMovie(
        keyword: String
    ): Single<MovieRepo> =
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword
        )
            .map {
                MovieRepo(
                    keyword = keyword,
                    movies = it.movies.map { movieItem ->
                        MovieItemMapper.reverseMap(movieItem)
                    }
                )
            }
            .flatMap {
                refreshMovieSearchHistory(
                    keyword = keyword,
                    movies = it.movies
                )
            }

    override fun getImage(
        keyword: String
    ): Single<ImageRepo> =
        naverSearchRemoteDataSource.getImage(
            keyword = keyword
        )
            .map {
                ImageRepo(
                    keyword = keyword,
                    images = it.images.map { imageItem ->
                        ImageItemMapper.reverseMap(imageItem)
                    }
                )
            }
            .flatMap {
                refreshImageSearchHistory(
                    keyword = keyword,
                    images = it.images
                )
            }

    override fun getBlog(
        keyword: String
    ): Single<BlogRepo> =
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword
        )
            .map {
                BlogRepo(
                    keyword = keyword,
                    blogs = it.blogs.map { blogItem ->
                        BlogItemMapper.reverseMap(blogItem)
                    }
                )
            }
            .flatMap {
                refreshBlogSearchHistory(
                    keyword = keyword,
                    blogs = it.blogs
                )
            }

    override fun getKin(
        keyword: String
    ): Single<KinRepo> =
        naverSearchRemoteDataSource.getKin(
            keyword = keyword
        )
            .map {
                KinRepo(
                    keyword = keyword,
                    kins = it.kins.map { kinItem ->
                        KinItemMapper.reverseMap(kinItem)
                    }
                )
            }
            .flatMap {
                refreshKinSearchHistory(
                    keyword = keyword,
                    kins = it.kins
                )
            }

    override fun getLatestMovieResult(): Single<MovieRepo> =
        naverSearchLocalDataSource.getMovie()
            .map {
                MovieRepo(
                    keyword = naverSearchLocalDataSource.getLatestMovieKeyword(),
                    movies = it.movies.map { entity ->
                        MovieEntityMapper.reverseMap(entity)
                    }
                )
            }

    override fun getLatestImageResult(): Single<ImageRepo> =
        naverSearchLocalDataSource.getImage()
            .map {
                ImageRepo(
                    keyword = naverSearchLocalDataSource.getLatestImageKeyword(),
                    images = it.images.map { entity ->
                        ImageEntityMapper.reverseMap(entity)
                    }
                )
            }

    override fun getLatestBlogResult(): Single<BlogRepo> =
        naverSearchLocalDataSource.getBlog()
            .map {
                BlogRepo(
                    keyword = naverSearchLocalDataSource.getLatestBlogKeyword(),
                    blogs = it.blogs.map { entity ->
                        BlogEntityMapper.reverseMap(entity)
                    }
                )
            }

    override fun getLatestKinResult(): Single<KinRepo> =
        naverSearchLocalDataSource.getKin()
            .map {
                KinRepo(
                    keyword = naverSearchLocalDataSource.getLatestKinKeyword(),
                    kins = it.kins.map { entity ->
                        KinEntityMapper.reverseMap(entity)
                    }
                )
            }

    override fun refreshMovieSearchHistory(
        keyword: String,
        movies: List<Movie>
    ): Single<MovieRepo> {
        val movieRepo = MovieRepo(
            keyword = keyword,
            movies = movies
        )

        return if (movies.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearMovieResult() },
                fun2 = { naverSearchLocalDataSource.saveMovieKeyword(keyword) }
            )
                .toSingle { movieRepo }
        } else {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearMovieResult() },
                fun2 = { naverSearchLocalDataSource.saveMovieKeyword(keyword) },
                fun3 = {
                    naverSearchLocalDataSource.saveMovieResult(
                        movies.map { MovieEntityMapper.map(it) }
                    )
                }
            )
                .toSingle { movieRepo }
        }
    }

    override fun refreshImageSearchHistory(
        keyword: String,
        images: List<Image>
    ): Single<ImageRepo> {
        val imageRepo = ImageRepo(
            keyword = keyword,
            images = images
        )

        return if (images.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearImageResult() },
                fun2 = { naverSearchLocalDataSource.saveImageKeyword(keyword) }
            )
                .toSingle { imageRepo }
        } else {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearImageResult() },
                fun2 = { naverSearchLocalDataSource.saveImageKeyword(keyword) },
                fun3 = {
                    naverSearchLocalDataSource.saveImageResult(
                        images.map { ImageEntityMapper.map(it) }
                    )
                }
            )
                .toSingle { imageRepo }
        }
    }

    override fun refreshBlogSearchHistory(keyword: String, blogs: List<Blog>): Single<BlogRepo> {
        val blogRepo = BlogRepo(
            keyword = keyword,
            blogs = blogs
        )

        return if (blogs.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearBlogResult() },
                fun2 = { naverSearchLocalDataSource.saveBlogKeyword(keyword) }
            )
                .toSingle { blogRepo }
        } else {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearBlogResult() },
                fun2 = { naverSearchLocalDataSource.saveBlogKeyword(keyword) },
                fun3 = {
                    naverSearchLocalDataSource.saveBlogResult(
                        blogs.map { BlogEntityMapper.map(it) }
                    )
                }
            )
                .toSingle { blogRepo }
        }
    }

    override fun refreshKinSearchHistory(keyword: String, kins: List<Kin>): Single<KinRepo> {
        val kinRepo = KinRepo(
            keyword = keyword,
            kins = kins
        )

        return if (kins.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearKinResult() },
                fun2 = { naverSearchLocalDataSource.saveKinKeyword(keyword) }
            )
                .toSingle { kinRepo }
        } else {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearKinResult() },
                fun2 = { naverSearchLocalDataSource.saveKinKeyword(keyword) },
                fun3 = {
                    naverSearchLocalDataSource.saveKinResult(
                        kins.map { KinEntityMapper.map(it) }
                    )
                }
            )
                .toSingle { kinRepo }
        }
    }

    private fun updateSearchHistory(
        fun1: () -> Unit,
        fun2: () -> Unit,
        fun3: (() -> Unit)? = null
    ): Completable {
        val firstCall = Completable.fromCallable(fun1)
        val secondCall = Completable.fromCallable(fun2)
        val completable = firstCall
            .andThen(secondCall)

        return fun3?.let { call ->
            val thirdCall = Completable.fromCallable(call)
            completable
                .andThen(thirdCall)
        } ?: run {
            completable
        }
    }
}