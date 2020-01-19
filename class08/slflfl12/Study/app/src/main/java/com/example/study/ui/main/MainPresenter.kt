package com.example.study.ui.main

import com.example.study.data.model.Movie
import com.example.study.data.repository.NaverSearchRepository
import com.example.study.data.repository.NaverSearchRepositoryImpl
import io.reactivex.Single

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun getMovies(query: String): Single<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}