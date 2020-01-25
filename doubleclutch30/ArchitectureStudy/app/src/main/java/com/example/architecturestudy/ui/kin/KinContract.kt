package com.example.architecturestudy.ui.kin

import com.example.architecturestudy.data.model.KinItem

interface KinContract {

    interface View {
        fun showResult(item : List<KinItem>)
        fun showErrorMessage(message : String)
        fun showEmpty(message: String)
    }

    interface Presenter {
        fun taskSearch(isNetwork: Boolean, keyword: String)
        fun getLastData()
    }
}