package com.ironelder.androidarchitecture.view.mainview

import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.view.baseview.BaseContract
import io.reactivex.Single

interface MainContract {
    interface View : BaseContract.View {
        fun onDataChanged(result: ArrayList<ResultItem>)
        fun showErrorMessage(msg: String?)
        fun showNoSearchData()
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun search(
            type: String,
            query: String?,
            defaultMsg: String?
        )

        fun searchWithAdapter(
            type: String,
            query: String?
        ):Single<TotalModel>
    }
}