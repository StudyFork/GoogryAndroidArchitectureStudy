package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.database.entity.BlogItemEntity
import com.jay.architecturestudy.data.database.entity.ImageItemEntity
import com.jay.architecturestudy.data.database.entity.KinItemEntity
import com.jay.architecturestudy.data.database.entity.MovieItemEntity
import com.jay.architecturestudy.data.model.*
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

class NaverSearchRepositoryImpl(
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
                    kins = it.kins
                )
            }

    override fun getLatestMovieResult(): Single<MovieRepo> =
        naverSearchLocalDataSource.getMovie()
            .map {
                MovieRepo(
                    keyword = naverSearchLocalDataSource.getLatestMovieKeyword(),
                    movies = it)
            }

    override fun getLatestImageResult(): Single<ImageRepo> =
        naverSearchLocalDataSource.getImage()
            .map {
                ImageRepo(
                    keyword = naverSearchLocalDataSource.getLatestImageKeyword(),
                    images = it
                )
            }

    override fun getLatestBlogResult(): Single<BlogRepo> =
        naverSearchLocalDataSource.getBlog()
            .map {
                BlogRepo(
                    keyword = naverSearchLocalDataSource.getLatestBlogKeyword(),
                    blogs = it
                )
            }

    override fun getLatestKinResult(): Single<KinRepo> =
        naverSearchLocalDataSource.getKin()
            .map {
                KinRepo(
                    keyword = naverSearchLocalDataSource.getLatestKinKeyword(),
                    kins = it
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
            val movieList = ensureMovieEntityList(movies)
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearMovieResult() },
                fun2 = { naverSearchLocalDataSource.saveMovieKeyword(keyword) },
                fun3 = { naverSearchLocalDataSource.saveMovieResult(movieList) }
            )
                .toSingle { movieRepo }
        }
    }

    private fun ensureMovieEntityList(movies: List<Movie>): List<MovieItemEntity> =
        arrayListOf<MovieItemEntity>().apply {
            movies.mapTo(this) { movie ->
                MovieItemEntity(
                    title = movie.title,
                    link = movie.link,
                    image = movie.image,
                    subtitle = movie.subtitle,
                    director = movie.director,
                    actor = movie.actor,
                    pubDate = movie.pubDate,
                    userRating = movie.userRating
                )
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
            val imageList = ensureImageEntityList(images)
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearImageResult() },
                fun2 = { naverSearchLocalDataSource.saveImageKeyword(keyword) },
                fun3 = { naverSearchLocalDataSource.saveImageResult(imageList) }
            )
                .toSingle { imageRepo }
        }
    }


    private fun ensureImageEntityList(images: List<Image>): List<ImageItemEntity> =
        arrayListOf<ImageItemEntity>().apply {
            images.mapTo(this) { image ->
                ImageItemEntity(
                    link = image.link,
                    sizeWidth = image.sizeWidth,
                    sizeHeight = image.sizeHeight,
                    thumbnail = image.thumbnail,
                    title = image.title
                )
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
            val blogList = ensureBlogEntityList(blogs)
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearBlogResult() },
                fun2 = { naverSearchLocalDataSource.saveBlogKeyword(keyword) },
                fun3 = { naverSearchLocalDataSource.saveBlogResult(blogList) }
            )
                .toSingle { blogRepo }
        }
    }

    private fun ensureBlogEntityList(blogs: List<Blog>): List<BlogItemEntity> =
        arrayListOf<BlogItemEntity>().apply {
            blogs.mapTo(this) { blog ->
                BlogItemEntity(
                    bloggerLink = blog.bloggerLink,
                    bloggerName = blog.bloggerName,
                    description = blog.description,
                    link = blog.link,
                    postdate = blog.postdate,
                    title = blog.title
                )
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
            val kinList = ensureKinEntityList(kins)
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearKinResult() },
                fun2 = { naverSearchLocalDataSource.saveKinKeyword(keyword) },
                fun3 = { naverSearchLocalDataSource.saveKinResult(kinList) }
            )
                .toSingle { kinRepo }
        }
    }

    private fun ensureKinEntityList(kins: List<Kin>): List<KinItemEntity> =
        arrayListOf<KinItemEntity>().apply {
            kins.mapTo(this) { kin ->
                KinItemEntity(
                    description = kin.description,
                    link = kin.link,
                    title = kin.title
                )
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