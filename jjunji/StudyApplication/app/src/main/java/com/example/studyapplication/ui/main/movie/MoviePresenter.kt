package com.example.studyapplication.ui.main.movie

import com.example.studyapplication.data.model.SearchMovieResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.network.Conn

class MoviePresenter (val view: MovieContract.View, private val repository: NaverSearchRepository) : MovieContract.Presenter {
    override fun clickSearchButton(query: String) {
        repository.getMovieList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData : SearchMovieResult? = result as SearchMovieResult
                searchData?.let {
                    view.showList(searchData.arrMovieInfo)
                }
            }

            override fun failed(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

//            override fun failed(errorMessage: String) {
//                view.toastErrorConnFailed(errorMessage)
//            }
        })
    }

}