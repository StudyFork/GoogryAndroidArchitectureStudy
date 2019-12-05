package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseContract
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem

interface ContentsContract : BaseContract {
    interface View : BaseContract.View {
        fun showQueryResult(resultList: List<ContentItem>)

        fun showErrorQueryEmpty()

        fun showErrorLoadFail()

        fun showErrorResultEmpty()

    }

    interface Presenter : BaseContract.Presenter {
        fun loadContents(type: Tab, query: String)
    }
}