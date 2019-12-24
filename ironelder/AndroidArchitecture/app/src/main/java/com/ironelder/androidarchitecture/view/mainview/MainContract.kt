package com.ironelder.androidarchitecture.view.mainview

import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import com.ironelder.androidarchitecture.view.baseview.BaseContract

interface MainContract {

    interface View : BaseContract.View {

        fun onDataChanged(result: ArrayList<ResultItem>)
        fun showErrorMessage(msg: String?)
        fun showNoSearchData()
        fun showLoading()
        fun hideLoading()
        fun onLoadFromDatabase(
            searchWord: String,
            result: ArrayList<ResultItem>
        )

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun searchWithAdapter(
            type: String,
            query: String?,
            searchResultDatabase: SearchResultDatabase?
        )

        fun getSearchResultToRoom(
            type: String,
            searchResultDatabase: SearchResultDatabase?
        )

    }

}