package com.buddman1208.architecturestudy.ui

import com.buddman1208.architecturestudy.models.CommonResponse

interface BaseContract {

    interface View {

        fun updateData(data: CommonResponse)
        fun showNoResult()
        fun showError(msg : String = "")
        fun showLoading()
        fun hideLoading()

    }

    interface Presenter {
        fun searchByQuery(
            query : String,
            type: String
        )
    }

}