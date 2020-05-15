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


class NaverMovieLocalDataSourceImpl : NaverMovieLocalDataSource {
    @SuppressLint("CheckResult")
    override fun saveMovieList(data: ArrayList<Movie.Items>, context: Context?) {
        context?.let { nContext ->
            Thread(Runnable {
                MovieRoomDataBase.getInstance(nContext)?.getMovieDAO()?.deleteAll()
            }).start()

            Observable.fromIterable(data)
                .subscribeOn(Schedulers.io())
                .subscribe({ eachItem ->
                    val localData = MovieLocal()
                    localData.apply {
                        title = eachItem.title
                        subtitle = eachItem.subtitle
                        image = eachItem.image
                        link = eachItem.link
                        pubDate = eachItem.pubDate
                        director = eachItem.director
                        actor = eachItem.actor
                        userRating = eachItem.userRating
                    }
                    MovieRoomDataBase.getInstance(nContext)?.getMovieDAO()?.insert(localData)
                    Log.d("bsjbsj", "RoomDatabase Save Success $localData")
                },
                    {
                        Log.d("bsjbsj", "RoomDatabase Save Failure")
                    })
        }
    }

    @SuppressLint("CheckResult")
    override fun getMovieList(
        context: Context?,
        Success: (ArrayList<MovieLocal>) -> Unit,
        Failure: (Throwable) -> Unit
    ) {
        context?.let { nContext ->
            MovieRoomDataBase.getInstance(nContext)?.getMovieDAO()?.getMovieList()
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (it.count() > 0) {
                        val arrList = ArrayList<MovieLocal>()
                        arrList.addAll(it)
                        Success.invoke(arrList)
                        Log.d("bsjbsj", "RoomDatabase GetData Success : ${arrList[0]}")
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


}