package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.UrlResource

interface BaseContract {
    interface View {
        fun hideKeyboard()
        fun showUrlResource(item: UrlResource)
        fun showInvalidUrl()
        fun showQueryEmpty()
        fun showNoSearchResult()
        fun showSearchFailed(e: Exception)
    }

    interface Presenter {
        fun selectUrlItem(item: UrlResource)
    }
}