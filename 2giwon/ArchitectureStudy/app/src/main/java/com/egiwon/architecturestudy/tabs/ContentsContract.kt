package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.base.BaseContract
import com.egiwon.architecturestudy.base.BasePresenter
import com.egiwon.architecturestudy.data.Content

interface ContentsContract {
    interface View : BaseContract.View<BasePresenter> {
        fun onUpdateUi(contents: List<Content.Item>)
        fun onFail(throwable: Throwable)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadContents(type: String, query: String)
    }
}