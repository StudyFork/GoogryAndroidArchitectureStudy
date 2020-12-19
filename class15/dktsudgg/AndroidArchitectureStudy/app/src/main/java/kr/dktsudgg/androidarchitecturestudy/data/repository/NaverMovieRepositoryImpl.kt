package kr.dktsudgg.androidarchitecturestudy.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.dktsudgg.androidarchitecturestudy.data.datasource.local.NaverMovieLocalDataSource
import kr.dktsudgg.androidarchitecturestudy.data.datasource.remote.NaverMovieRemoteDataSource
import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse
import javax.inject.Inject
import javax.inject.Singleton

class NaverMovieRepositoryImpl @Inject constructor(
    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource,
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource
) : NaverMovieRepository {

    override fun searchMovies(
        query: String,
        successCallback: (NaverMovieResponse) -> Unit,
        failureCallback: (t: Throwable) -> Unit
    ) {
        // 영화 검색
        naverMovieRemoteDataSource.searchMovies(query, null, null, successCallback, failureCallback)
        // 영화 검색 이력 추가
        if (query != "")
            naverMovieLocalDataSource.putMovieSearchHistory(query)
    }

    override fun getMovieSearchHistory(): List<String> {
        return naverMovieLocalDataSource.getMovieSearchHistory()
    }
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class NaverMovieRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNaverMovieRepository(naverMovieRepositoryImpl: NaverMovieRepositoryImpl): NaverMovieRepository
}