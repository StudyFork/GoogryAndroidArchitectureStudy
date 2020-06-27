package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.local.room.MovieItemDao
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource
import io.reactivex.Observable

class NaverMovieRepositoryImpl(
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource
) : NaverMovieRepository {

    override fun getCashedMovieList() : Observable<List<MovieLocalItem>> = naverMovieLocalDataSource.getMovieList()

    override fun saveMovieList(movieList: MovieLocalItem): Observable<Unit> = naverMovieLocalDataSource.saveMovieList(movieList)

    override fun deleteMovieList(): MovieItemDao = naverMovieLocalDataSource.deleteMovieList()

    override fun getMovieList(keyWord: String) = naverMovieRemoteDataSource.getMovieList(keyWord)


//    companion object {
//
//        private var INSTANCE: NaverMovieRepositoryImpl? = null
//
//        fun getInstance(
//            naverMovieLocalDataSource: NaverMovieLocalDataSource,
//            naverMovieRemoteDataSource: NaverMovieRemoteDataSource
//        ): NaverMovieRepositoryImpl {
//            return INSTANCE ?: NaverMovieRepositoryImpl(
//                naverMovieLocalDataSource,
//                naverMovieRemoteDataSource
//            )
//                .apply { INSTANCE = this }
//        }
//
//    }

}