package com.jay.architecturestudy.ui.blog

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl

class BlogPresenter(
    private val view: BlogContract.View,
    private val repository: NaverSearchRepositoryImpl
) : BlogContract.Presenter {

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword,
            success = { responseBlog ->
                view.updateResult(responseBlog.blogs)
            },
            fail = { e ->
                val message = e.message ?: return@getBlog
                view.showErrorMessage(message)
            }
        )
    }
}