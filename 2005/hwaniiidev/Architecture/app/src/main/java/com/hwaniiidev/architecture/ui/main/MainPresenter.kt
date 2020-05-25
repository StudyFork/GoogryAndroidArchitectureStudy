package com.hwaniiidev.architecture.ui.main

import android.util.Log
import com.hwaniiidev.architecture.data.repository.NaverMovieRepositoryImpl

class MainPresenter(
    private val view: MainContract.View,
    private val naverMovieRepositoryImpl: NaverMovieRepositoryImpl
) : MainContract.Presenter {
    private val TAG = MainPresenter::class.java.simpleName

    override fun searchMovies(searchValue: String) {
        if (searchValue.isNullOrEmpty()) {
            view.showQueryIsEmpty()
        } else {
            naverMovieRepositoryImpl.getRemoteMovies(
                query = searchValue,
                onSuccess = { response ->
                    view.hideKeyBoard()

                    if (response.total == 0) {
                        view.showResponseIsNone()
                    } else {
                        view.showMoviesList(response.items)
                    }
                },
                onError = { errorMessage ->
                    view.showResponseError()
                    Log.d(TAG, errorMessage)
                },
                onFailure = { t ->
                    view.showNetworkFailure()
                    Log.d(TAG, t.toString())
                }
            )
        }
    }
}