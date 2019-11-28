package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.base.BaseContract
import com.egiwon.architecturestudy.data.Content

interface ContentsContract : BaseContract {
    interface View : BaseContract.View {
        fun onUpdateUi(resultList: List<Content.Item>)
        fun onFail(throwable: Throwable)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadContents(type: String, query: String)
    }
}