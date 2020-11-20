package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.UrlResource

interface BaseContract {
    interface View {
        fun hideKeyboard()
        fun showUrlResource(item: UrlResource)
        fun showInvalidUrl()
    }

    interface Presenter {
        fun selectUrlItem(item: UrlResource)
    }
}