package com.jay.architecturestudy.ui.blog

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BasePresenter

class BlogPresenter(
    override val view: BlogContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BasePresenter(view, repository), BlogContract.Presenter {

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword,
            success = { responseBlog ->
                view.updateResult(responseBlog.blogs)
            },
            fail = { e ->
                handlerError(e)
            }
        )
    }
}