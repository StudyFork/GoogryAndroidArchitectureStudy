package com.example.kotlinapplication.ui.view.home.presenter

import android.annotation.SuppressLint
import com.example.kotlinapplication.data.model.MovieItem
import com.example.kotlinapplication.data.repository.DataRepositoryImpl
import com.example.kotlinapplication.ui.view.page.PageContract

class MoviePresenter(listener: PageContract.View<MovieItem>) :
    PageContract.Presenter {
    private val view: PageContract.View<MovieItem> = listener
    private val dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()

    @SuppressLint("CheckResult")
    override fun loadData(query: String) {
        dataRepositoryImpl.callMovieResources(query).subscribe(
            { datas -> view.getItems(datas.items) },
            { errorMessage -> view.getError("error 에러" + errorMessage) })
    }
}
