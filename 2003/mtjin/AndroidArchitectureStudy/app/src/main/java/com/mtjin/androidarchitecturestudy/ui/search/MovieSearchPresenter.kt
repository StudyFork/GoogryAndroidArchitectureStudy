package com.mtjin.androidarchitecturestudy.ui.search

import android.util.Log
import com.mtjin.androidarchitecturestudy.data.source.MovieRepository
import retrofit2.HttpException

class MovieSearchPresenter(
    private val view: MovieSearchContract.View,
    private val movieRepository: MovieRepository
) :
    MovieSearchContract.Presenter {

    override fun requestMovie(query: String) {
        if (query.isEmpty()) {
            view.showEmptyQuery()
        } else {
            view.showWait()
            view.showLoading()
            view.scrollResetState()
            movieRepository.getSearchMovies(query,
                success = {
                    if (it.isEmpty()) {
                        view.showNoMovie()
                    } else {
                        view.adapterClear()
                        view.adapterSetItems(it)
                        view.showNetworkSuccess()
                    }
                    view.hideLoading()
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> view.showNetworkError()
                        else -> view.showToast(it.message.toString())
                    }
                    view.hideLoading()
                })
        }
    }

    override fun requestPagingMovie(query: String, offset: Int) {
        view.showLoading()
        movieRepository.getPagingMovies(query, offset,
            success = {
                if (it.isEmpty()) {
                    view.showLastPage()
                } else {
                    view.adapterSetItems(it)
                    view.showNetworkSuccess()
                }
                view.hideLoading()
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> view.showNetworkError()
                    else -> view.showToast(it.message.toString())
                }
                view.hideLoading()
            })
    }

    companion object {
        const val TAG = "MovieSearchTAG"
    }
}