package com.byiryu.data

import com.byiryu.data.model.Item
import com.byiryu.data.source.local.LocalDataSource
import com.byiryu.data.source.remote.RemoteDataSource
import io.reactivex.Single
import java.util.concurrent.TimeUnit

internal class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): Repository{
    private val ID = "test"
    private val PW = "test"

    override fun getMovieList(query: String): Single<List<Item>> {
        localDataSource.setPrevQuery(query)
        return localDataSource.getAll()
            .flatMap { movies ->
                if (movies.isEmpty()) {
                    getMovieListWithRemote(query)
                        .doOnSuccess {
                            localDataSource.saveMovies(it)
                        }
                } else {
                    Single.just(movies)
                }
            }
    }

    override fun getMovieListWithRemote(query: String): Single<List<Item>> {
        return remoteDataSource.getMoveList(query)
            .map { it.items }
    }

    override fun getPrevSearchQuery(): String {
        return localDataSource.getPrevSearchQuery()
    }

    override fun isAutoLogin(): Boolean {
        return localDataSource.isAutoLogin()
    }

    override fun setAutoLogin() {
        localDataSource.setAutoLogin()
    }

    override fun loginCheck(id: String?, pw: String?): Single<Boolean> {
        return if (id == ID && pw == PW) {
            Single.just(true).delay(2, TimeUnit.SECONDS)
        } else {
            Single.just(false).delay(2, TimeUnit.SECONDS)
        }
    }


}

//
//class Repository constructor(
//    private var remoteDataSource: RemoteDataSource,
//    private var localDataSource: LocalDataSource
//) {
//    fun getMovieList(
//        query: String
//    ): Single<List<MovieItem>> {
//        localDataSource.setPrevQuery(query)
//        return localDataSource.getAll()
//            .flatMap { movies ->
//                if (movies.isEmpty()) {
//                    getMovieListWithRemote(query)
//                        .doOnSuccess {
//                            localDataSource.saveMovies(it)
//                        }
//                } else {
//                    Single.just(movies)
//                }
//            }
//    }
//
//    private fun getMovieListWithRemote(query: String): Single<List<MovieItem>> {
//        return remoteDataSource.getMoveList(query)
//            .map { it.items }
//    }
//
//
//    fun getPrevSearchQuery(): String {
//        return localDataSource.getPrevSearchQuery()
//    }
//
//    fun isAutoLogin(): Boolean {
//        return localDataSource.isAutoLogin()
//    }
//
//    fun setAutoLogin() {
//        localDataSource.setAutoLogin()
//    }
//
//    fun loginCheck(id: String?, pw: String?): Single<Boolean> {
//        return if (id == ID && pw == PW) {
//            Single.just(true).delay(2, TimeUnit.SECONDS)
//        } else {
//            Single.just(false).delay(2, TimeUnit.SECONDS)
//        }
//    }
//
//
//}