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
    var successCallback: ((ArrayList<Movie.Items>) -> Unit)? = null

    override fun sendMovieList(success: (ArrayList<Movie.Items>) -> Unit) {
        successCallback = success
    }

    @SuppressLint("CheckResult")
    override fun saveMovieList(context: Context) {

        successCallback = { data ->
            Thread(Runnable {
                roomDataBase?.getMovieDAO()?.deleteAll()
            }).start()

            Observable.fromIterable(data)
                .subscribeOn(Schedulers.io())
                .subscribe({ eachItem ->
                    val localData = MovieLocal().apply {
                        title = eachItem.title
                        subtitle = eachItem.subtitle
                        image = eachItem.image
                        link = eachItem.link
                        pubDate = eachItem.pubDate
                        director = eachItem.director
                        actor = eachItem.actor
                        userRating = eachItem.userRating
                    }

                    roomDataBase?.getMovieDAO()?.insert(localData)
                    Log.d("bsjbsj", "RoomDatabase Save Success $localData")
                },
                    {
                        Log.d("bsjbsj", "RoomDatabase Save Failure")
                    })

        }
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