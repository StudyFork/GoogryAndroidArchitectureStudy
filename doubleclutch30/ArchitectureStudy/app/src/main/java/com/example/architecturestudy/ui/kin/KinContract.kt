package com.example.architecturestudy.ui.kin

import com.example.architecturestudy.data.model.KinItem

interface KinContract {

    interface View {
        fun showResult(item : List<KinItem>)
        fun showErrorMessage(message : String)
    }

    interface Presenter {
        fun taskError(error : Throwable)
        fun taskSearch(keyword: String)
    }
}