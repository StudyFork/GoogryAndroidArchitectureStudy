package com.mtjin.androidarchitecturestudy.data.source

import com.mtjin.androidarchitecturestudy.data.Movie
import com.mtjin.androidarchitecturestudy.data.source.local.MovieLocalDataSource
import com.mtjin.androidarchitecturestudy.data.source.remote.MovieRemoteDataSource
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {

    private var movieList = ArrayList<Movie>()
    override fun getSearchMovies(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        //TODO: 추후 네트워크 상태로 분기처리하게 변경
        movieList.clear()
        // 1. local 에서 검색
        runBlocking {
            launch {
                movieList = movieLocalDataSource.getSearchMovies(query) as ArrayList<Movie>
            }
        }

        // 2. remote 에서 검색
        movieRemoteDataSource.getSearchMovies(
            query,
            success = {
                // remote 에서 검색 성공한 경우 remote 데이터 전달
                runBlocking {
                    launch {
                        movieLocalDataSource.insertMovies(it)
                        movieList = it as ArrayList<Movie>
                        success(movieList)
                    }
                }
            },
            fail = {
                runBlocking {
                    launch {
                        if (movieList.isEmpty()) {
                            fail(it)
                        } else {
                            // 로컬 데이터 전달
                            success(movieList)
                        }
                    }
                }
            }
        )
    }
}