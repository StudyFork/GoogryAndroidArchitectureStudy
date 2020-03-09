package com.mtjin.androidarchitecturestudy.data.source

import android.util.Log
import com.mtjin.androidarchitecturestudy.data.Movie
import com.mtjin.androidarchitecturestudy.data.source.local.MovieLocalDataSource
import com.mtjin.androidarchitecturestudy.data.source.remote.MovieRemoteDataSource
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {
    override fun getSearchMovies(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        movieRemoteDataSource.getSearchMovies(
            query, success = {
                if (it.isEmpty()) {
                    // remote 에서 검색 못한 경우 로컬에서 검색
                    Log.d(TAG, "Repository Local 검색 시작")
                    runBlocking {
                        launch {
                            success(movieLocalDataSource.getSearchMovies(query))
                        }
                    }
                } else {
                    // remote 에서 검색 성공한 경우 로컬에 저장
                    Log.d(TAG, "Repository Local  INSERT 시작")
                    runBlocking {
                        launch {
                            movieLocalDataSource.insertMovies(it)
                            success(it)
                        }
                    }
                }
            },
            fail = {
                // retmote 에서 검색 실패한경우 로컬에서 검색
                Log.d(TAG, "Repository Local 검색 시작2")
                runBlocking {
                    launch {
                        success(movieLocalDataSource.getSearchMovies(query))
                    }
                }
            }
        )
    }

    companion object {
        const val TAG = "MovieRepositoryImpl"
    }


}