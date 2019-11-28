package com.ironelder.androidarchitecture.view.mainview

import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.source.SearchDataSourceImpl
import com.ironelder.androidarchitecture.view.baseview.BasePresenter
import io.reactivex.Single

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {
    override fun search(
        type: String,
        query: String?,
        defaultMsg: String?
    ) {
        if (query.isNullOrEmpty()) {
            view.showErrorMessage(defaultMsg)
        } else {
            view.showLoading()
            SearchDataSourceImpl.getDataForSearch(
                type,
                query,
                ::onSuccess,
                ::onFail
            )
        }
    }

    override fun searchWithAdapter(type: String, query: String?): Single<TotalModel> {
        view.showLoading()
        return SearchDataSourceImpl.getDataForSearchWithAdapter(type, query)
    }

    fun onSuccess(result: TotalModel) {
        view.hideLoading()
        if (result.items.isNullOrEmpty()) {
            view.showNoSearchData()
        } else {
            view.onDataChanged(result.items)
        }
    }

    fun onFail(msg: String) {
        view.hideLoading()
        view.showErrorMessage(msg)
    }

    fun onError(t: Throwable) {
        view.hideLoading()
        view.showErrorMessage(t.message)
    }
}