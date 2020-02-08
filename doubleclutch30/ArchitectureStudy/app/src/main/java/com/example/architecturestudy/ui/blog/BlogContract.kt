package com.example.architecturestudy.ui.blog

import com.example.architecturestudy.data.model.BlogItem

interface BlogContract {

    interface View {
        fun showErrorMessage(message : String)
        fun showResult(item : List<BlogItem>)
        fun showEmpty(message: String)
    }

    interface Presenter {
        fun taskSearch(isNetWork: Boolean, keyword : String)
        fun getLastData()
        fun onStop()
    }
}