package kr.dktsudgg.androidarchitecturestudy.data.repository

import kr.dktsudgg.androidarchitecturestudy.data.datasource.remote.NaverMovieDataSource
import kr.dktsudgg.androidarchitecturestudy.data.datasource.remote.NaverMovieDataSourceImpl
import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse

class NaverMovieRepositoryImpl : NaverMovieRepository {

    private val naverMovieDataSource: NaverMovieDataSource = NaverMovieDataSourceImpl()

    override fun searchMovies(
        query: String,
        successCallback: (NaverMovieResponse?) -> Unit,
        failureCallback: (t: Throwable) -> Unit
    ) {
        naverMovieDataSource.searchMovies(query, null, null, successCallback, failureCallback)
    }
}