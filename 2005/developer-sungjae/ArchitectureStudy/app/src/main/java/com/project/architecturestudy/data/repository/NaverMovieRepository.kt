package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.NaverApiData
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable
import io.reactivex.Single

interface NaverMovieRepository {

    fun getMovieList(
        keyWord: String,
        onGetRemoteData: (Single<NaverApiData>) -> Unit
    )

    fun getCashedMovieList(onSuccess: (Observable<List<MovieLocalItem>>) -> Unit, onFailure: (t: Throwable) -> Unit)
}