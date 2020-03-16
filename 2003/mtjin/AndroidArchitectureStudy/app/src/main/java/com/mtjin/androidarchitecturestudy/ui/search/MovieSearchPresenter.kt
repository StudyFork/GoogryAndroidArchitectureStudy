package com.mtjin.androidarchitecturestudy.ui.search

import android.util.Log
import com.mtjin.androidarchitecturestudy.utils.MyApplication
import retrofit2.HttpException

class MovieSearchPresenter(
    private val view: MovieSearchContract.View,
    private val myApplication: MyApplication,
    private val movieAdapter: MovieAdapter
) :
    MovieSearchContract.Presenter {

    override fun requestMovie(query: String) {

        if (query.isEmpty()) {
            view.showToast("검색어를 입력해주세요.")
        } else {
            view.showToast("잠시만 기다려주세요.")
            view.showLoading()
            view.scrollResetState()
            myApplication.movieRepository.getSearchMovies(query,
                success = {
                    if (it.isEmpty()) {
                        view.showToast("해당 영화는 존재하지 않습니다.")
                    } else {
                        movieAdapter.clear()
                        movieAdapter.setItems(it)
                        view.showToast("영화를 불러왔습니다.")
                    }
                    view.hideLoading()
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> view.showToast("네트워크에 문제가 있습니다.")
                        else -> view.showToast(it.message.toString())
                    }
                    view.hideLoading()
                })
        }
    }

    override fun requestPagingMovie(query: String, offset: Int) {
        view.showLoading()
        myApplication.movieRepository.getPagingMovies(query, offset,
            success = {
                if (it.isEmpty()) {
                    view.showToast("마지막 페이지입니다.")
                } else {
                    movieAdapter.setItems(it)
                    view.showToast("영화를 불러왔습니다.")
                }
                view.hideLoading()
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> view.showToast("네트워크에 문제가 있습니다.")
                    else -> view.showToast(it.message.toString())
                }
                view.hideLoading()
            })
    }

    companion object {
        const val TAG = "MovieSearchTAG"
    }
}