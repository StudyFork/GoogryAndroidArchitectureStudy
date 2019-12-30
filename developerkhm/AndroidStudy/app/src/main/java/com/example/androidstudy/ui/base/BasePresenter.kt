package com.example.androidstudy.ui.base

import com.example.androidstudy.api.data.TotalModel
import com.example.androidstudy.model.repository.NaverDataRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BasePresenter(val view : BaseContract.View) : BaseContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun search(query: String?, type: String) {
            NaverDataRepositoryImpl.getNaverSearchData(
                type,
                query!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.showLoading()
                }
                .doAfterTerminate {
                    view.hideLoading()
                }
                .subscribe(::onSuccess, ::onError)
                .addDisposable()
        }

    private fun onSuccess(result: TotalModel) {
        if (result.items.isNullOrEmpty()) {
            view.showNoSearchData()
        } else {
            view.onDataChanged(result.items)
        }
    }

    private fun onError(t: Throwable) {
        println(t.message)
        view.showErrorMessage(t.message)
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}