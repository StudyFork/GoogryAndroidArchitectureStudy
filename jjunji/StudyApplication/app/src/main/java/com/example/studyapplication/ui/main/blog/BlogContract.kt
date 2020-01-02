package com.example.studyapplication.ui.main.blog

import com.example.studyapplication.data.model.SearchBlogResult
import com.example.studyapplication.ui.main.base.BaseSearchContract

interface BlogContract {
    interface View : BaseSearchContract.View {
        fun showList(items: Array<SearchBlogResult.BlogInfo>)
    }

    interface Presenter : BaseSearchContract.Presenter {
        fun clickSearchButton(query : String)
    }
}