package com.example.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.example.data.ConstValue
import com.example.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LocalDataSourceImpl(private val context: Context) :
    LocalDataSource {

    private val sharedPref : SharedPreferences by lazy {
        context.getSharedPreferences(
            ConstValue.SHARED_MOVIE_CACHE_NAME,
            Context.MODE_PRIVATE
        )
    }

    private val roomDb : RoomDb by lazy {
        RoomDb.getInstance(context)
    }

    override fun getMovieData() : Single<List<Movie>> =  roomDb.movieDao.getAllMovies()

    override fun saveMovieData(movies: List<Movie>) {

        Completable.fromRunnable {
            Runnable {
                roomDb.movieDao.deleteAll()
                roomDb.movieDao.insertAll(movies)
            }.run()
        }.subscribeOn(Schedulers.io())
            .subscribe()

    }

    override fun getCacheKeyword(): String
            = sharedPref.getString(ConstValue.SHARED_MOVIE_CACHE_KEYWORD, "").toString()

    override fun saveCacheKeyword(keyword: String) {
        with(sharedPref.edit()){
            putString(ConstValue.SHARED_MOVIE_CACHE_KEYWORD, keyword)
            commit()
        }
    }

    override fun clearCacheKeyword() {
        with(sharedPref.edit()){
            remove(ConstValue.SHARED_MOVIE_CACHE_KEYWORD)
            commit()
        }
    }

}