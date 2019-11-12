package com.ironelder.androidarchitecture.view.mainview

import com.ironelder.androidarchitecture.data.ResultItem

interface MainContract {
    interface View {
        fun onDataChanged(result: ArrayList<ResultItem>)
        fun showErrorMessage(msg: String)
        fun showNoSearchData()
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun search(
            type: String,
            query: String
        )

    }
}