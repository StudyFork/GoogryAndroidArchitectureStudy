package com.example.myapplication.ui

import androidx.room.Room
import com.example.myapplication.Movie
import com.example.myapplication.MovieDatabase
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.repository.NaverRepositoryImpl
import com.example.myapplication.data.source.NaverLocalDataSourceImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    override fun findMovie(query: String) {
        query ?: view.queryNone()
        NaverRepositoryImpl.getResultData(query,
            success = {
                if (it.items.isEmpty()) {
                    view.resultNone()
                } else {
                    view.updateMovieRecycler(it.items)
                }
            },
            fail = { view.failMovieGet(it.message.toString()) })


    }

    override fun resentData(db: MovieDatabase) {
        NaverLocalDataSourceImpl.getResentData(db)
    }

    override fun saveCache(db: MovieDatabase, movies: List<MovieResult.Item>){
        NaverLocalDataSourceImpl.saveCache(db, movies)
    }

    override fun delMovies(db: MovieDatabase) {
        NaverLocalDataSourceImpl.delMovie(db)
    }


}