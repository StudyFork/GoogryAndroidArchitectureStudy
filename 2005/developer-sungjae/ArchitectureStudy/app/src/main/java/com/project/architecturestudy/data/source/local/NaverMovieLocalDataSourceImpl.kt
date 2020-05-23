package com.project.architecturestudy.data.source.local

import android.content.Context
import android.util.Log
import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.data.model.NaverApiData
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import com.project.architecturestudy.data.source.local.room.MovieRoomDataBase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class NaverMovieLocalDataSourceImpl(context: Context) : NaverMovieLocalDataSource {

    override var roomDataBase = MovieRoomDataBase.getInstance(context)
    override val disposable = CompositeDisposable()

    override fun saveMovieList(data: Single<NaverApiData>) {

        disposable.add(data.doOnSubscribe {
            roomDataBase.getMovieDao().deleteAll()

        }
            .subscribeOn(Schedulers.io())
            .subscribe({
                for (item in it.items) {
                    Log.d("bsjbsj", "RoomDataBase Subscribe Item:$item\n")
                    val localData = MovieLocalItem().apply {

                        this.title = item.title
                        this.subtitle = item.subtitle
                        this.image = item.image
                        this.link = item.link
                        this.pubDate = item.pubDate
                        this.director = item.director
                        this.actor = item.actor
                        this.userRating = item.userRating
                    }

                    roomDataBase.getMovieDao().insert(localData)
                    Log.d("bsjbsj", "RoomDatabase Save Success $localData")
                }
            },
                {
                    Log.d("bsjbsj", "RoomDatabase Save Failure:$it")
                })
        )
    }


    override fun getMovieList(
        onSuccess: (ArrayList<MovieItem>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        roomDataBase.getMovieDao().getMovieList()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("bsjbsj", "RoomDatabase Get Data Success:$it")
                if (it.isNotEmpty()) {
                    val movieList = ArrayList<MovieItem>()
                    for (localItem in it) {
                        val item = MovieItem().apply {

                            this.title = localItem.title
                            this.subtitle = localItem.subtitle
                            this.image = localItem.image
                            this.link = localItem.link
                            this.pubDate = localItem.pubDate
                            this.director = localItem.director
                            this.actor = localItem.actor
                            this.userRating = localItem.userRating
                        }

                        movieList.add(item)
                    }
                    onSuccess.invoke(movieList)
                } else {
                    Log.d("bsjbsj", "RoomDatabase has no Data")
                }
            },
                {
                    onFailure.invoke(it)
                    Log.d("bsjbsj", "RoomDatabase GetData Failure")
                })?.let {
                disposable.add(it)
            }
    }

    override fun dispose() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}