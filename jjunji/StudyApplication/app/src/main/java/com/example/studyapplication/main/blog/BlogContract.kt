package com.example.studyapplication.main.blog

import com.example.studyapplication.data.model.SearchBlogResult

interface BlogContract {
    interface View {
        fun showList(items: Array<SearchBlogResult.BlogInfo>)
    }

    interface UserActions {
        fun clickSearchButton(query : String)
    }
}