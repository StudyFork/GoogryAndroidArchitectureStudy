package com.ironelder.androidarchitecture.view.mainview

import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.repository.SearchDataRepositoryImpl
import com.ironelder.androidarchitecture.view.baseview.BasePresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun searchWithAdapter(type: String, query: String?) {
        SearchDataRepositoryImpl.getDataForSearch(type, query, ::getObservable)
    }

    private fun getObservable(observable:Single<TotalModel>){

        observable
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
}