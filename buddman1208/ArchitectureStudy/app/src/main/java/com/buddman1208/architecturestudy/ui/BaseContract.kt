package com.buddman1208.architecturestudy.ui

import com.buddman1208.architecturestudy.models.CommonResponse
import com.buddman1208.architecturestudy.utils.ErrorType

interface BaseContract {

    interface View {

        fun updateData(data: CommonResponse)
        fun showNoResult()
        fun showError(errorType : ErrorType)
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