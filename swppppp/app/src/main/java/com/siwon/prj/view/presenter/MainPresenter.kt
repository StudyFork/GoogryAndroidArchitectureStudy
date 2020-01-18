package com.siwon.prj.view.presenter

import com.siwon.prj.repository.MovieSearchRepository
import com.siwon.prj.repository.MovieSearchRepositoryImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter{

    val repository: MovieSearchRepository = MovieSearchRepositoryImpl()

    override fun getSearchResult(input: String) {
        // 입력값 없는경우
        if (input.isNullOrBlank()) {
            view.emptyInput()
            return
        }
        repository.searchMovies(input,
            success = {result ->
                result.let {
                    if (it.isNullOrEmpty()) view.emptyResult()
                    else view.showResult(it)
                }
            },
            fail = {
                view.serviceErr(it.message.toString())
            })
    }
}