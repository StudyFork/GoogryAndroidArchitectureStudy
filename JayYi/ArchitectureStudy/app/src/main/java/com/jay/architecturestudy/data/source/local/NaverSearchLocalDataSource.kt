package com.jay.architecturestudy.data.source.local

import android.content.SharedPreferences
import com.jay.architecturestudy.data.database.SearchHistoryDatabase
import com.jay.architecturestudy.data.database.entity.BlogItemEntity
import com.jay.architecturestudy.data.database.entity.ImageItemEntity
import com.jay.architecturestudy.data.database.entity.KinItemEntity
import com.jay.architecturestudy.data.database.entity.MovieItemEntity
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.model.Movie
import io.reactivex.Single

interface NaverSearchLocalDataSource {
    val searchHistoryDatabase: SearchHistoryDatabase
    val sharedPreferences: SharedPreferences

    fun getMovie(): Single<List<Movie>>

    fun getImage(): Single<List<Image>>

    fun getBlog(): Single<List<Blog>>

    fun getKin(): Single<List<Kin>>

    fun saveMovieResult(movies: List<MovieItemEntity>)

    fun saveImageResult(images: List<ImageItemEntity>)

    fun saveBlogResult(blogs: List<BlogItemEntity>)

    fun saveKinResult(kins: List<KinItemEntity>)

    fun clearMovieResult()

    fun clearImageResult()

    fun clearBlogResult()

    fun clearKinResult()

    fun saveMovieKeyword(keyword: String)

    fun saveImageKeyword(keyword: String)

    fun saveBlogKeyword(keyword: String)

    fun saveKinKeyword(keyword: String)

    fun getLatestMovieKeyword(): String

    fun getLatestImageKeyword(): String

    fun getLatestBlogKeyword(): String

    fun getLatestKinKeyword(): String

    fun clearMovieKeyword()

    fun clearImageKeyword()

    fun clearBlogKeyword()

    fun clearKinKeyword()

    companion object {
        const val PREFS_NAME = "search_history"
        const val PREFS_KEY_MOVIE = "movie"
        const val PREFS_KEY_BLOG = "blog"
        const val PREFS_KEY_IMAGE = "image"
        const val PREFS_KEY_KIN = "kin"
    }
}