package com.example.architecturestudy.ui.blog

import com.example.architecturestudy.data.model.BlogItem

interface BlogContract {

    interface View {
        fun showErrorMessage(message : String)
        fun showResult(item : List<BlogItem>)
    }

    interface Presenter {
        fun taskSearch(keyword : String)
    }
}