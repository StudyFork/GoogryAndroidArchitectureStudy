package com.ironelder.androidarchitecture.view.mainview

import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.source.SearchDataSourceImpl
import com.ironelder.androidarchitecture.view.baseview.BasePresenter

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

    private fun onSuccess(result: TotalModel) {
        view.hideLoading()
        if (result.items.isNullOrEmpty()) {
            view.showNoSearchData()
        } else {
            view.onDataChanged(result.items)
        }
    }

    private fun onFail(msg: String) {
        view.hideLoading()
        view.showErrorMessage(msg)
    }
}