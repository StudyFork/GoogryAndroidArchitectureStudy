package com.buddman1208.architecturestudy.ui

import android.util.Log
import com.buddman1208.architecturestudy.models.CommonResponse
import com.buddman1208.architecturestudy.repo.NaverDataRepositoryImpl
import com.buddman1208.architecturestudy.utils.ErrorType
import com.buddman1208.architecturestudy.utils.addToCompositeDisposable
import com.buddman1208.architecturestudy.utils.subscribeOnIO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class BasePresenter(val view : BaseContract.View) : BaseContract.Presenter {

    private val disposables : CompositeDisposable = CompositeDisposable()

    override fun searchByQuery(query: String, type: String) {
        val query = query.trim()
        if (query.isNotBlank()) {
            NaverDataRepositoryImpl
                .searchByTypeFromNaver(
                    searchType = type,
                    query = query
                )
                .subscribeOnIO()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading() }
                .doAfterSuccess { view.hideLoading() }
                .subscribe(
                    { onDataSuccess(it) },
                    { onDataFailure(it) }
                )
                .addToCompositeDisposable(disposables)

        } else {
            view.showError(ErrorType.NO_QUERY)
        }
    }

    private fun onDataSuccess(it: CommonResponse) {
        if (it.items.isEmpty()) {
            view.showNoResult()
        } else {
            view.updateData(it)
        }
    }

    private fun onDataFailure(it: Throwable) {
        view.showError(ErrorType.CONNECTION_ERROR)
        Log.e("asdf", it.localizedMessage)
    }

}