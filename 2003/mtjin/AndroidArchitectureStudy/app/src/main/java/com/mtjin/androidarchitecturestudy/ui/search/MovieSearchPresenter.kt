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
            view.showEmptyQueryToast()
        } else {
            view.showWaitToast()
            view.showLoading()
            view.scrollResetState()
            movieRepository.getSearchMovies(query,
                success = {
                    if (it.isEmpty()) {
                        view.showNoMovieToast()
                    } else {
                        view.adapterClear()
                        view.adapterSetItems(it)
                        view.showNetworkSuccessToast()
                    }
                    view.hideLoading()
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> view.showNetworkErrorToast()
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
                    view.showLastPageToast()
                } else {
                    view.adapterSetItems(it)
                    view.showNetworkSuccessToast()
                }
                view.hideLoading()
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> view.showNetworkErrorToast()
                    else -> view.showToast(it.message.toString())
                }
                view.hideLoading()
            })
    }

    companion object {
        const val TAG = "MovieSearchTAG"
    }
}