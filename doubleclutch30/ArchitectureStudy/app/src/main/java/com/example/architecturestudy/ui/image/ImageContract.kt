package com.example.architecturestudy.ui.image

import com.example.architecturestudy.data.model.ImageItem

interface ImageContract {

    interface View {
        fun showResult(item : List<ImageItem>)
        fun showErrorMessage(message : String)
        fun showEmpty(message: String)
    }

    interface Presenter {
        fun taskSearch(isNetWork: Boolean, keyword: String)
        fun getLastData()
        fun onStop()
    }
}