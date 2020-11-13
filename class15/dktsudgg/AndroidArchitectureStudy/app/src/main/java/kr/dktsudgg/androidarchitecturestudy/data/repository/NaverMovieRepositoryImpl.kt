package kr.dktsudgg.androidarchitecturestudy.data.repository

import kr.dktsudgg.androidarchitecturestudy.data.datasource.remote.NaverMovieRemoteDataSource
import kr.dktsudgg.androidarchitecturestudy.data.datasource.remote.NaverMovieRemoteDataSourceImpl
import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse

class NaverMovieRepositoryImpl : NaverMovieRepository {

    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl()

    override fun searchMovies(
        query: String,
        successCallback: (NaverMovieResponse) -> Unit,
        failureCallback: (t: Throwable) -> Unit
    ) {
        naverMovieRemoteDataSource.searchMovies(query, null, null, successCallback, failureCallback)
    }
}