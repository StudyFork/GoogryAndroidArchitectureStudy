package com.example.architecturestudy.ui.blog

import com.example.architecturestudy.data.repository.NaverSearchRepository

class BlogPresenter(
    val view: BlogContract.View,
    private val repository: NaverSearchRepository?
) : BlogContract.Presenter{
    override fun taskSearch(isNetwork: Boolean, keyword : String) {
        repository?.getBlog(
            isNetwork = isNetwork,
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> view.showErrorMessage(e.toString()) }
        )
    }

    override fun getLastData() {
        repository?.getLastBlog(
            success = { view.showResult(it) },
            fail = { view.showEmpty("empty data") }
        )
    }
}