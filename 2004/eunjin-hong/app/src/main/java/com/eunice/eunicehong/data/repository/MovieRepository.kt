package com.eunice.eunicehong.data.repository

import android.content.Context
import com.eunice.eunicehong.data.local.MovieLocalDataSource
import com.eunice.eunicehong.data.model.Movie
import com.eunice.eunicehong.data.model.MovieList
import com.eunice.eunicehong.data.remote.MovieRemoteDataSource
import com.eunice.eunicehong.util.ToastMessage

object MovieRepository {
    private val remoteDataSource = MovieRemoteDataSource()
    private val localDataSource = MovieLocalDataSource

    fun getMovieList(
        context: Context,
        query: String,
        success: (movieList: List<Movie>) -> Unit,
        failure: (e: Throwable) -> Unit
    ) {
        val onRemoteLoadSuccess = { list: MovieList ->
            localDataSource.saveMovieList(context, query, list)
            success(list.items)
        }

        try {
            val list = localDataSource.getMovieList(context, query)
            if (list.isEmpty()) {

                remoteDataSource.getMovieList(query, onRemoteLoadSuccess, failure)
            } else {
                success(list)
                ToastMessage.show(context, "☝️ 저장된 결과를 가져왔습니다.")
            }
        } catch (e: NullPointerException) {
            remoteDataSource.getMovieList(query, onRemoteLoadSuccess, failure)
        }
    }

    fun removeAllHistory(context: Context) {
        localDataSource.removeMovieHistory(context)
    }
}