package com.example.architecturestudy.ui.blog

import com.example.architecturestudy.Injection

class BlogPresenter(val view : BlogContract.View) : BlogContract.Presenter{

    private val repository by lazy { Injection.provideNaverSearchRepository() }

    override fun taskSearch(keyword : String) {
        repository.getBlog(
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> view.showErrorMessage(e.toString()) }
        )
    }

}