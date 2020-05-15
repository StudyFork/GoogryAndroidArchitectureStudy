package com.project.architecturestudy.data.source.remote

import android.annotation.SuppressLint
import com.project.architecturestudy.components.RetrofitService
import com.project.architecturestudy.data.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NaverMovieRemoteDataSourceImpl : NaverMovieRemoteDataSource {

    override val service = RetrofitService.create()

    @SuppressLint("CheckResult")
    override fun getMovieList(
        keyWord: String,
        Success: (ArrayList<Movie.Items>) -> Unit,
        Failure: (Throwable) -> Unit
    ) {
        service.getMovies(keyWord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Success.invoke(it.items)
            }, { error ->
                Failure.invoke(error)
            })
    }

}