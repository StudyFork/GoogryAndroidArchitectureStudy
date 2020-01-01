package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.database.entity.BlogEntity
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.model.*
import io.reactivex.Flowable
import io.reactivex.Single

interface NaverSearchRepository {

    fun getMovie(keyword: String) : Single<ResponseMovie>

    fun getImage(keyword: String) : Single<ResponseImage>

    fun getBlog(keyword: String) : Single<ResponseBlog>

    fun getKin(keyword: String) : Single<ResponseKin>

    fun getLatestMovieResult() : Single<List<Movie>>

    fun getLatestImageResult() : Single<List<Image>>

    fun getLatestBlogResult() : Single<List<Blog>>

    fun getLatestKinResult() : Single<List<Kin>>

    fun saveMovieResult(movies : List<MovieEntity>)

    fun saveImageResult(images: List<ImageEntity>)

    fun saveBlogResult(blogs: List<BlogEntity>)

    fun saveKinResult(kins: List<KinEntity>)

    fun clearMovieResult()

    fun clearImageResult()

    fun clearBlogResult()

    fun clearKinResult()

    fun saveMovieKeyword(keyword: String)

    fun saveImageKeyword(keyword: String)

    fun saveBlogKeyword(keyword: String)

    fun saveKinKeyword(keyword: String)

    fun getLatestMovieKeyword() : String

    fun getLatestImageKeyword() : String

    fun getLatestBlogKeyword() : String

    fun getLatestKinKeyword() : String

    fun clearMovieKeyword()

    fun clearImageKeyword()

    fun clearBlogKeyword()

    fun clearKinKeyword()

}