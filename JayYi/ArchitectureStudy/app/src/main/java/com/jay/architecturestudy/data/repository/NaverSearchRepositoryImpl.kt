package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.database.entity.BlogEntity
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.model.*
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource
) : NaverSearchRepository {

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

    override fun getLatestMovieResult(): Single<List<Movie>> =
        naverSearchLocalDataSource.getMovie()

    override fun getLatestImageResult(): Single<List<Image>> =
        naverSearchLocalDataSource.getImage()

    override fun getLatestBlogResult(): Single<List<Blog>> =
        naverSearchLocalDataSource.getBlog()

    override fun getLatestKinResult(): Single<List<Kin>> =
        naverSearchLocalDataSource.getKin()

    override fun saveMovieResult(movies: List<MovieEntity>) {
        naverSearchLocalDataSource.saveMovieResult(
            movies = movies
        )
    }

    override fun saveImageResult(images: List<ImageEntity>) {
        naverSearchLocalDataSource.saveImageResult(
            images = images
        )
    }

    override fun saveBlogResult(blogs: List<BlogEntity>) {
        naverSearchLocalDataSource.saveBlogResult(
            blogs = blogs
        )
    }

    override fun saveKinResult(kins: List<KinEntity>) {
        naverSearchLocalDataSource.saveKinResult(
            kins = kins
        )
    }

    override fun clearMovieResult() {
        naverSearchLocalDataSource.clearMovieResult()
    }

    override fun clearImageResult() {
        naverSearchLocalDataSource.clearImageResult()
    }

    override fun clearBlogResult() {
        naverSearchLocalDataSource.clearBlogResult()
    }

    override fun clearKinResult() {
        naverSearchLocalDataSource.clearKinResult()
    }

    override fun saveMovieKeyword(keyword: String) {
        naverSearchLocalDataSource.saveMovieKeyword(keyword)
    }

    override fun saveImageKeyword(keyword: String) {
        naverSearchLocalDataSource.saveImageKeyword(keyword)
    }

    override fun saveBlogKeyword(keyword: String) {
        naverSearchLocalDataSource.saveBlogKeyword(keyword)
    }

    override fun saveKinKeyword(keyword: String) {
        naverSearchLocalDataSource.saveKinKeyword(keyword)
    }

    override fun getLatestMovieKeyword(): String =
        naverSearchLocalDataSource.getLatestMovieKeyword()

    override fun getLatestImageKeyword(): String =
        naverSearchLocalDataSource.getLatestImageKeyword()


    override fun getLatestBlogKeyword(): String =
        naverSearchLocalDataSource.getLatestBlogKeyword()

    override fun getLatestKinKeyword(): String =
        naverSearchLocalDataSource.getLatestKinKeyword()


    override fun clearMovieKeyword() {
        naverSearchLocalDataSource.clearMovieKeyword()
    }

    override fun clearImageKeyword() {
        naverSearchLocalDataSource.clearImageKeyword()
    }

    override fun clearBlogKeyword() {
        naverSearchLocalDataSource.clearBlogKeyword()
    }

    override fun clearKinKeyword() {
        naverSearchLocalDataSource.clearKinKeyword()
    }
}