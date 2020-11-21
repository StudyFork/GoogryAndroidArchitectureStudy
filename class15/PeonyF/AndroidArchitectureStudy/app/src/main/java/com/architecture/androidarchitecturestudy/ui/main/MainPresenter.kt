package com.architecture.androidarchitecturestudy.ui.main

import android.util.Log
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.ui.base.BasePresenter

class MainPresenter(
    private val view: MainContract.View, private val movieRepository: MovieRepositoryImpl
) : BasePresenter(), MainContract.Presenter {
    override fun findMovie(keyword: String) {
        if (keyword.isEmpty()) {
            view.showErrorMessage("영화 제목을 입력해주세요")
        } else {
            movieRepository.getMovieData(
                keyword,
                30,
                onEmptyList = { view.showErrorMessage("검색결과가 없습니다") },
                onSuccess = { view.updateMovieRecycler(it.items as ArrayList<Movie>) },
                onFailure = { Log.e("Api is fail", it.toString()) })
        }
        movieRepository.getMovieData(keyword, 30,
            onSuccess = {
                if (it.items == null) {
                    view.showMessage("검색결과가 없습니다")
                } else {
                    view.updateMovieRecycler(it.items as ArrayList<Movie>)
                }

            },
            onFailure = { Log.e("Api is fail", it.toString()) }
        )
        view.removeKeyboard()
    }
}