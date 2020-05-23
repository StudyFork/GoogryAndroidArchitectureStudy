package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single

interface NaverMovieRepository {

    fun getMovieList(
        keyWord: String,
        onGetRemoteData: (Single<NaverApiData>) -> Unit
    )

    fun getCashedMovieList(Success: (ArrayList<MovieItem>) -> Unit, Failure: (t: Throwable) -> Unit)
    fun dispose()

}