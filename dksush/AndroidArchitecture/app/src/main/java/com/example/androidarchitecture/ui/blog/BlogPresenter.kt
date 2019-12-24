package com.example.androidarchitecture.ui.blog

import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.ui.base.BaseContract

class BlogPresenter(
    private val view: BaseContract.View<BlogData>,
    private val repoInterface: NaverRepoInterface
) :
    BaseContract.Presenter {
    override fun requestList(text: String) {
        repoInterface.getBlog(text, 1, 10,
            success = {
                view.renderItems(it)
            }, fail = {
                view.errorToast(it.toString())
            })
    }
}