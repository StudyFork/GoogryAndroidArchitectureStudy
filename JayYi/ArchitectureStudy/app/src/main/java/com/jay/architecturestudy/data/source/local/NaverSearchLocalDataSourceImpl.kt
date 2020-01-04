package com.jay.architecturestudy.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.jay.architecturestudy.MainApplication
import com.jay.architecturestudy.data.database.SearchHistoryDatabase
import com.jay.architecturestudy.data.database.entity.BlogItemEntity
import com.jay.architecturestudy.data.database.entity.ImageItemEntity
import com.jay.architecturestudy.data.database.entity.KinItemEntity
import com.jay.architecturestudy.data.database.entity.MovieItemEntity
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_KEY_BLOG
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_KEY_IMAGE
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_KEY_KIN
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_KEY_MOVIE
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource.Companion.PREFS_NAME
import io.reactivex.Single

object NaverSearchLocalDataSourceImpl : NaverSearchLocalDataSource {

    private val context: Context
        get() = MainApplication.instance

    override val searchHistoryDatabase: SearchHistoryDatabase by lazy {
        SearchHistoryDatabase.getInstance(context)
    }

    override val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun getMovie(): Single<List<Movie>> =
        searchHistoryDatabase.movieDao()
            .getAll()
            .map {
                val movies = arrayListOf<Movie>().apply {
                    it.mapTo(this) { entity ->
                        Movie(
                            title = entity.title,
                            link = entity.link,
                            subtitle = entity.subtitle,
                            actor = entity.actor,
                            pubDate = entity.pubDate,
                            _userRating = (entity.userRating * 2.toFloat()).toString(),
                            image = entity.image,
                            director = entity.director
                        )
                    }
                }
                movies
            }

    override fun getImage(): Single<List<Image>> =
        searchHistoryDatabase.imageDao()
            .getAll()
            .map {
                val images = arrayListOf<Image>().apply {
                    it.mapTo(this) { entity ->
                        Image(
                            link = entity.link,
                            sizeHeight = entity.sizeHeight,
                            sizeWidth = entity.sizeWidth,
                            thumbnail = entity.thumbnail,
                            title = entity.title
                        )
                    }
                }
                images
            }

    override fun getBlog(): Single<List<Blog>> =
        searchHistoryDatabase.blogDao()
            .getAll()
            .map {
                val blogs = arrayListOf<Blog>()
                it.mapTo(blogs) { entity ->
                    Blog(
                        bloggerLink = entity.bloggerLink,
                        bloggerName = entity.bloggerName,
                        description = entity.description,
                        link = entity.link,
                        postdate = entity.postdate,
                        title = entity.title
                    )
                }
                blogs
            }

    override fun getKin(): Single<List<Kin>> =
        searchHistoryDatabase.kinDao()
            .getAll()
            .map {
                val kins = arrayListOf<Kin>()
                it.mapTo(kins) { entity ->
                    Kin(
                        description = entity.description,
                        link = entity.link,
                        title = entity.title
                    )
                }
                kins
            }

    override fun saveMovieResult(movies: List<MovieItemEntity>) {
        searchHistoryDatabase.movieDao().insertAll(movies)
    }

    override fun saveImageResult(images: List<ImageItemEntity>) {
        searchHistoryDatabase.imageDao().insertAll(images)
    }

    override fun saveBlogResult(blogs: List<BlogItemEntity>) {
        searchHistoryDatabase.blogDao().insertAll(blogs)
    }

    override fun saveKinResult(kins: List<KinItemEntity>) {
        searchHistoryDatabase.kinDao().insertAll(kins)
    }

    override fun clearMovieResult() {
        searchHistoryDatabase.movieDao().clearAll()
    }

    override fun clearImageResult() {
        searchHistoryDatabase.imageDao().clearAll()
    }

    override fun clearBlogResult() {
        searchHistoryDatabase.blogDao().clearAll()
    }

    override fun clearKinResult() {
        searchHistoryDatabase.kinDao().clearAll()
    }

    override fun saveMovieKeyword(keyword: String) {
        sharedPreferences.edit()
            .putString(PREFS_KEY_MOVIE, keyword)
            .apply()
    }

    override fun saveImageKeyword(keyword: String) {
        sharedPreferences.edit()
            .putString(PREFS_KEY_IMAGE, keyword)
            .apply()
    }

    override fun saveBlogKeyword(keyword: String) {
        sharedPreferences.edit()
            .putString(PREFS_KEY_BLOG, keyword)
            .apply()
    }

    override fun saveKinKeyword(keyword: String) {
        sharedPreferences.edit()
            .putString(PREFS_KEY_KIN, keyword)
            .apply()
    }

    override fun getLatestMovieKeyword(): String =
        sharedPreferences.getString(PREFS_KEY_MOVIE, "") ?: ""

    override fun getLatestImageKeyword(): String =
        sharedPreferences.getString(PREFS_KEY_IMAGE, "") ?: ""

    override fun getLatestBlogKeyword(): String =
        sharedPreferences.getString(PREFS_KEY_BLOG, "") ?: ""

    override fun getLatestKinKeyword(): String =
        sharedPreferences.getString(PREFS_KEY_KIN, "") ?: ""

    override fun clearMovieKeyword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearImageKeyword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearBlogKeyword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearKinKeyword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
