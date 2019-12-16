package com.example.androidstudy.ui.base

import com.example.androidstudy.api.data.Item

interface BaseContract {

    interface View {
        fun onDataChanged(result: ArrayList<Item>)
        fun showErrorMessage(msg: String?)
        fun showNoSearchData()
        fun showLoading()
        fun hideLoading()
        fun hideKeyboard(view: View)
    }

    interface Presenter {
        fun search(
            query: String?,
            type: String
        )
    }
}