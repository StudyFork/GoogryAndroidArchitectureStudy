package app.ch.study.data.repository

import app.ch.study.data.local.source.NaverQueryLocalDataSourceImpl
import app.ch.study.data.remote.response.MovieResponse
import app.ch.study.data.remote.source.NaverQueryRemoteDataSourceImpl
import io.reactivex.Observable
import io.reactivex.Single

class NaverQueryRepositoryImple(
    private val naverQueryLocalDataSource: NaverQueryLocalDataSourceImpl,
    private val naverQueryRemoteDataSource: NaverQueryRemoteDataSourceImpl
) : NaverQueryRepository {

    override fun searchMovie(query: String): Observable<MovieResponse> {
        val localResponse: Single<MovieResponse> =
            Single.just(naverQueryLocalDataSource.searchMovie())

        return localResponse
            .concatWith(naverQueryRemoteDataSource
                .searchMovie(query)
                .doOnSuccess {
                    naverQueryLocalDataSource.saveMovieList(it.items)
                    naverQueryLocalDataSource.saveSearchQuery(query)
                }
            )
            .toObservable()
    }

}