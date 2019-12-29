package com.example.studyapplication.ui.main.blog

import com.example.studyapplication.data.model.SearchBlogResult

interface BlogContract {
    interface View {
        fun showList(items: Array<SearchBlogResult.BlogInfo>)
    }

    interface Presenter {
        fun clickSearchButton(query : String)
    }
}