package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.model.*
import io.reactivex.Single

interface NaverSearchRepository {

    fun getMovie(keyword: String): Single<MovieRepo>

    fun getImage(keyword: String): Single<ImageRepo>

    fun getBlog(keyword: String): Single<BlogRepo>

    fun getKin(keyword: String): Single<KinRepo>

    fun getLatestMovieResult(): Single<MovieRepo>

    fun getLatestImageResult(): Single<ImageRepo>

    fun getLatestBlogResult(): Single<BlogRepo>

    fun getLatestKinResult(): Single<KinRepo>

    fun refreshMovieSearchHistory(keyword: String, movies: List<Movie>): Single<MovieRepo>

    fun refreshImageSearchHistory(keyword: String, images: List<Image>): Single<ImageRepo>

    fun refreshBlogSearchHistory(keyword: String, blogs: List<Blog>): Single<BlogRepo>

    fun refreshKinSearchHistory(keyword: String, kins: List<Kin>): Single<KinRepo>

}