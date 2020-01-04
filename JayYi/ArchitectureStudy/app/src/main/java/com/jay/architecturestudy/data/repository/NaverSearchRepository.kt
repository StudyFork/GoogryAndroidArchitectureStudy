package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.model.*
import io.reactivex.Single

interface NaverSearchRepository {

    fun getMovie(keyword: String): Single<ResponseMovie>

    fun getImage(keyword: String): Single<ResponseImage>

    fun getBlog(keyword: String): Single<ResponseBlog>

    fun getKin(keyword: String): Single<ResponseKin>

    fun getLatestMovieResult(): Single<List<Movie>>

    fun getLatestImageResult(): Single<List<Image>>

    fun getLatestBlogResult(): Single<List<Blog>>

    fun getLatestKinResult(): Single<List<Kin>>

    fun refreshMovieSearchHistory(keyword: String, movies: List<Movie>): Single<List<Movie>>

    fun refreshImageSearchHistory(keyword: String, images: List<Image>): Single<List<Image>>

    fun refreshBlogSearchHistory(keyword: String, blogs: List<Blog>): Single<List<Blog>>

    fun refreshKinSearchHistory(keyword: String, kins: List<Kin>): Single<List<Kin>>

    fun getLatestMovieKeyword(): String

    fun getLatestImageKeyword(): String

    fun getLatestBlogKeyword(): String

    fun getLatestKinKeyword(): String

}