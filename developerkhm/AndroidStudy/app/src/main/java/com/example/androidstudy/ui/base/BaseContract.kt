package com.example.androidstudy.ui.base

import com.example.androidstudy.database.SearchResultDatabase
import com.example.androidstudy.model.data.Item
import com.example.androidstudy.model.data.SearchResultEntity

interface BaseContract {

    interface View {
        fun onDataChanged(result: ArrayList<Item>)
        fun onDataLoadLocal(query: String, result: ArrayList<Item>)
        fun showErrorMessage(msg: String?)
        fun showNoSearchData()
        fun showLoading()
        fun hideLoading()
        fun hideKeyboard(view: android.view.View)
    }

    interface Presenter {
        fun search(
            query: String?,
            type: String
        )
        fun searchLocal(
            type: String,
            searchResultDatabase: SearchResultDatabase?
        )

        fun insertSeachResult(vararg searchResult: SearchResultEntity)

        fun clearDisposable()
    }
}