package kr.dktsudgg.androidarchitecturestudy.data.repository

import kr.dktsudgg.androidarchitecturestudy.data.datasource.local.NaverMovieLocalDataSource
import kr.dktsudgg.androidarchitecturestudy.data.datasource.local.NaverMovieLocalDataSourceImpl
import kr.dktsudgg.androidarchitecturestudy.data.datasource.remote.NaverMovieRemoteDataSource
import kr.dktsudgg.androidarchitecturestudy.data.datasource.remote.NaverMovieRemoteDataSourceImpl
import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse

class NaverMovieRepositoryImpl : NaverMovieRepository {

    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl()
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource = NaverMovieLocalDataSourceImpl()

    override fun searchMovies(
        query: String,
        successCallback: (NaverMovieResponse) -> Unit,
        failureCallback: (t: Throwable) -> Unit
    ) {
        // 영화 검색
        naverMovieRemoteDataSource.searchMovies(query, null, null, successCallback, failureCallback)
        // 영화 검색 이력 추가
        if(query != "")
            naverMovieLocalDataSource.putMovieSearchHistory(query)
    }
}