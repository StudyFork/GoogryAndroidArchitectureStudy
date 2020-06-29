package com.project.data.impl

import com.project.data.NaverMovieLocalDataSource
import com.project.data.NaverMovieRemoteDataSource
import com.project.data.NaverMovieRepository
import com.project.data.model.MovieItem
import com.project.data.model.NaverMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

class NaverMovieRepositoryImpl(
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource
) : NaverMovieRepository {

    override fun getCashedMovieList(): Single<NaverMovieEntity> = naverMovieLocalDataSource.getMovieList()

    override fun saveMovieList(movieList: NaverMovieEntity): Completable = naverMovieLocalDataSource.saveMovieList(movieList)

    override fun deleteMovieList() {
        naverMovieLocalDataSource.deleteMovieList()
    }

    override fun getMovieList(keyWord: String) = naverMovieRemoteDataSource.getMovieList(keyWord)
}