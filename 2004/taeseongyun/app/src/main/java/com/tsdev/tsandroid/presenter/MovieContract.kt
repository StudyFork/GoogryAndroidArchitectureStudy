package com.tsdev.tsandroid.presenter

import com.tsdev.tsandroid.base.BaseContract
import com.tsdev.tsandroid.data.Item


interface MovieContract {
    interface View : BaseContract.View {
        fun showSearchResult(items: List<Item>)

        fun removeAll()
    }

    interface Presenter : BaseContract.Presenter {
        var isLoading: Boolean

        fun loadMovie(query: String)
    }
}