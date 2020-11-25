package kr.dktsudgg.androidarchitecturestudy.data.repository

import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse

interface NaverMovieRepository {

    fun searchMovies(
        query: String,
        successCallback: (NaverMovieResponse) -> Unit,
        failureCallback: (t: Throwable) -> Unit
    )

    fun getMovieSearchHistory(): List<String>

}