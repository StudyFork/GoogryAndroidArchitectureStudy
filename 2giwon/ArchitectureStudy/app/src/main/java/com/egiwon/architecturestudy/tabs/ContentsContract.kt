package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.base.BasePresenter
import com.egiwon.architecturestudy.base.BaseView
import com.egiwon.architecturestudy.data.Content

interface ContentsContract {
    interface View : BaseView<Presenter> {
        fun onUpdateUi(content: List<Content.Item>)
        fun onFail(throwable: Throwable)
    }

    interface Presenter : BasePresenter {
        fun loadContents(type: String, query: String)
    }
}