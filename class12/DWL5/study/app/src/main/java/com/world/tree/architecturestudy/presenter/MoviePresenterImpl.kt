package com.world.tree.architecturestudy.presenter

import com.world.tree.architecturestudy.model.repository.remote.NaverRepository

class MoviePresenterImpl(private val view: MovieContract.View,
                         private val repository: NaverRepository): MovieContract.Presenter {

    override fun searchMovie(q:String) {
        if (q.isBlank()) {
            view.showToast("검색어를 입력 해 주세요.")
        }
        repository.getMovies(q, success = {
            view.setMovieData(it)
        }, error ={
            it.message?.let {message ->
                view.showToast(message)
            }
        })
    }
}