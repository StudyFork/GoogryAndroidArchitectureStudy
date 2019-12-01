package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseContract
import com.egiwon.architecturestudy.data.Content

interface ContentsContract : BaseContract {
    interface View : BaseContract.View {
        fun showQueryResult(resultList: List<Content.Item>)

        fun showErrorQueryEmpty()

        fun showErrorLoadFail()

        fun showErrorResultEmpty()
    }

    interface Presenter : BaseContract.Presenter {
        fun loadContents(type: Tab, query: String)
    }
}