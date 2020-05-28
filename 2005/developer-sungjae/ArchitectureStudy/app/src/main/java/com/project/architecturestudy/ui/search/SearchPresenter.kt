package com.project.architecturestudy.ui.search

import android.util.Log
import com.project.architecturestudy.components.Constants.TAG
import com.project.architecturestudy.data.repository.NaverMovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchPresenter(
    private val view: SearchContract.View,
    private val naverMovieRepository: NaverMovieRepository
) : SearchContract.Presenter {

    override fun getMovieListFromLocal() {
        naverMovieRepository.getCashedMovieList(
            onSuccess = {
                Log.d(TAG, "getLocalData:$it")
                view.showMovieData(it.toList())
            },
            onFailure = {
                Log.d(TAG, "Throwable:$it")
            })
    }

    override fun getMovieListFromRemote(searchWord: String) {
        if (searchWord.isEmpty()) {
            view.showSearchWordIsEmptyMsg()
            return
        }

        naverMovieRepository.getMovieList(searchWord,
            onGetRemoteData = { single ->
                single.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            Log.d(TAG, "getRemoteData:$it")
                            view.showMovieData(it.items)
                            view.showRemoteDataSuccessMsg()
                        }, { t ->

                            view.showRemoteDataFailureMsg()
                            Log.d(TAG, t.toString())
                        })

            })
    }

    override fun remoteDispose() {
        naverMovieRepository.dispose()
    }
}