package com.project.architecturestudy.data.source.local

import android.util.Log
import com.project.architecturestudy.components.Constants.customTAG
import com.project.architecturestudy.data.source.local.room.MovieItemDao
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class NaverMovieLocalDataSourceImpl(private val movieItemDao: MovieItemDao) : NaverMovieLocalDataSource {

    override fun saveMovieList(data: MovieLocalItem) {
        movieItemDao.insert(data)
        Log.d(customTAG, "RoomDatabase Save Success $data")
    }


    override fun getMovieList(onSuccess: (Observable<List<MovieLocalItem>>) -> Unit, onFailure: (t: Throwable) -> Unit) {
        onSuccess.invoke(
            movieItemDao.getMovieList().subscribeOn(Schedulers.io())
        )
    }

    companion object {

        private var INSTANCE: NaverMovieLocalDataSourceImpl? = null

        fun getInstance(movieItemDao: MovieItemDao): NaverMovieLocalDataSourceImpl {
            synchronized(NaverMovieLocalDataSourceImpl::javaClass) {
                if (INSTANCE == null) {
                    INSTANCE = NaverMovieLocalDataSourceImpl(movieItemDao)
                }
                return INSTANCE!!
            }
        }
    }
}