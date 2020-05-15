package com.project.architecturestudy.data.repository

import android.content.Context
import com.project.architecturestudy.adapters.SearchAdapter
import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

object NaverMovieRepositoryImpl : NaverMovieRepository {

    override lateinit var context: Context
    override lateinit var naverMovieLocalDataSource: NaverMovieLocalDataSource
    override lateinit var naverMovieRemoteDataSource: NaverMovieRemoteDataSource
    private lateinit var adapter: SearchAdapter


    override fun setRepository(
        context: Context,
        naverMovieLocalDataSource: NaverMovieLocalDataSource,
        naverMovieRemoteDataSource: NaverMovieRemoteDataSource
    ) {
        this.context = context
        this.naverMovieLocalDataSource = naverMovieLocalDataSource
        this.naverMovieRemoteDataSource = naverMovieRemoteDataSource
    }

    override fun getCashedMovieList(Success: (ArrayList<MovieLocal>) -> Unit, Failure: (Throwable) -> Unit) {
        naverMovieLocalDataSource.getMovieList(context, Success, Failure)
    }

    override fun getMovieList(
        keyWord: String,
        Success: (ArrayList<Movie.Items>) -> Unit,
        Failure: (Throwable) -> Unit
    ) {
        naverMovieRemoteDataSource.getMovieList(keyWord, Success, Failure)
    }
//    Success = { items ->
//        adapter.setRemoteMovieData(items)
//        naverMovieLocalDataSource.saveMovieList(items, context)
//        Success.invoke()
//    },
//    Failure = {
//        Log.d("bsjbsj", "Failure : $it")
//        Failure.invoke()
//    }
}