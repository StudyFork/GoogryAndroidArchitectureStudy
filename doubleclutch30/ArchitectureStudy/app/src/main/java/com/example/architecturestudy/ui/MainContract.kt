package com.example.architecturestudy.ui

import com.example.architecturestudy.data.model.ImageItem

interface MainContract {

    interface View {
        fun showResult(item : List<ImageItem>)
        fun showErrorMessage(message : String)
    }

    interface Presenter {
        fun taskSearch(keyword : String)
    }
}