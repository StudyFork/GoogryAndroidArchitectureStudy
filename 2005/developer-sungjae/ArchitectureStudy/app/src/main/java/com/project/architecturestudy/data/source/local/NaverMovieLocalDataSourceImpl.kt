package com.project.architecturestudy.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.project.architecturestudy.data.source.local.room.MovieRoomDataBase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NaverMovieLocalDataSourceImpl(context: Context) : NaverMovieLocalDataSource {

    override var roomDataBase = MovieRoomDataBase.getInstance(context)

    @SuppressLint("CheckResult")
    override fun saveMovieList(context: Context, data: ArrayList<Movie.Items>) {
        Thread(Runnable {
            roomDataBase?.getMovieDAO()?.deleteAll()
        }).start()

        Observable.fromIterable(data)
            .subscribeOn(Schedulers.io())
            .subscribe({ eachItem ->
                val localData = MovieLocal().apply {

                    this.title = eachItem.title
                    this.subtitle = eachItem.subtitle
                    this.image = eachItem.image
                    this.link = eachItem.link
                    this.pubDate = eachItem.pubDate
                    this.director = eachItem.director
                    this.actor = eachItem.actor
                    this.userRating = eachItem.userRating
                }

                roomDataBase?.getMovieDAO()?.insert(localData)
                Log.d("bsjbsj", "RoomDatabase Save Success $localData")
            },
                {
                    Log.d("bsjbsj", "RoomDatabase Save Failure")
                })
    }

    @SuppressLint("CheckResult")
    override fun getMovieList(
        context: Context,
        Success: (ArrayList<MovieLocal>) -> Unit,
        Failure: (Throwable) -> Unit
    ) {
        roomDataBase?.getMovieDAO()?.getMovieList()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                if (it.count() > 0) {
                    val movieList = ArrayList<MovieLocal>()
                    movieList.addAll(it)
                    Success.invoke(movieList)
                    Log.d("bsjbsj", "RoomDatabase GetData Success : ${movieList[0]}")
                } else {
                    Log.d("bsjbsj", "RoomDatabase has no Data")
                }
            },
                {
                    Failure.invoke(it)
                    Log.d("bsjbsj", "RoomDatabase GetData Failure")
                })
    }
}