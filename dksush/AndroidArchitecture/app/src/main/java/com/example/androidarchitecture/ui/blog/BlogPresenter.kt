package com.example.androidarchitecture.ui.blog

import android.text.TextUtils
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.BlogData

class BlogPresenter(
    private val view: BlogContract.View<BlogData>,
    private val naverRepositroy: NaverRepoInterface
) :
    BlogContract.Presenter {


    override suspend fun requestSearchHist() {
        naverRepositroy.getBlogHist().let {
            if (it.isNotEmpty()) {
                view.isListEmpty(it.isEmpty())
                view.renderItems(it)
                view.inputKeyword(naverRepositroy.getBlogKeyword())

            }
        }


    }

    override fun requestList(text: String) {
        if (TextUtils.isEmpty(text)) {
            view.blankInputText()
        } else {
            naverRepositroy.saveBlogKeyword(text) // 검색어 쉐어드에 저장.
            naverRepositroy.getBlog(text, 1, 10,
                success = {
                    view.isListEmpty(false)
                    view.renderItems(it)
                }, fail = {
                    view.errorToast(it.toString())
                })
        }

    }


}