package app.ch.study.data.repository

import app.ch.study.data.local.source.NaverQueryLocalDataSource
import app.ch.study.data.remote.response.MovieResponse
import app.ch.study.data.remote.source.NaverQueryRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single

class NaverQueryRepositoryImpl(
    private val naverQueryLocalDataSource: NaverQueryLocalDataSource,
    private val naverQueryRemoteDataSource: NaverQueryRemoteDataSource
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

    override fun getQuery(): String
        = naverQueryLocalDataSource.getQuery()

}