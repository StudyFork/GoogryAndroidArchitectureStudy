package com.project.architecturestudy.ui.search

import android.content.Context
import android.util.Log
import android.view.View
import com.project.architecturestudy.R
import com.project.architecturestudy.components.Constants.customTAG
import com.project.architecturestudy.data.repository.NaverMovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class SearchPresenter(
    private val view: SearchContract.View,
    private val naverMovieRepository: NaverMovieRepository
) : SearchContract.Presenter {

    override fun getMovieListFromLocal() {
        naverMovieRepository.getCashedMovieList(
            onSuccess = {
                Log.d(customTAG, "getLocalData:$it")
                view.showMovieData(it.toList())
            },
            onFailure = {
                Log.d(customTAG, "Throwable:$it")
            })
    }

    override fun getMovieListFromRemote(context: Context, searchWord: String) {
        if (searchWord.isEmpty()) {
            view.showSearchWordIsEmptyMsg()
            return
        }

        val result = String.format(Locale.KOREAN, context.getString(R.string.tv_result_text), searchWord)
        view.showSearchKeyWord(result, View.VISIBLE)

        naverMovieRepository.getMovieList(searchWord,
            onGetRemoteData = { single ->
                single.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            Log.d(customTAG, "getRemoteData:$it")
                            view.showMovieData(it.items)
                            view.showRemoteDataSuccessMsg()
                        }, { t ->

                            view.showRemoteDataFailureMsg()
                            Log.d(customTAG, t.toString())
                        })

            })
    }

    override fun remoteDispose() {
        naverMovieRepository.dispose()
    }
}