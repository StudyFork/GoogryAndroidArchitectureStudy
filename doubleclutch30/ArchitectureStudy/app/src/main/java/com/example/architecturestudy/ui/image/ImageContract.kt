package com.example.architecturestudy.ui.image

import com.example.architecturestudy.data.model.ImageItem

interface ImageContract {

    interface View {
        fun showResult(item : List<ImageItem>)
        fun showErrorMessage(message : String)
    }

    interface Presenter {
        fun taskError(error : Throwable)
        fun taskSearch(keyword : String)
    }
}