package com.jay.architecturestudy.ui.blog

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo

class BlogPresenter(
    override val view: BlogContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), BlogContract.Presenter {

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword
        )
            .subscribe({ responseBlog ->
                view.updateResult(responseBlog.blogs)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }
}