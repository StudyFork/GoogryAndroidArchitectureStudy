package com.example.architecturestudy.ui.image

import com.example.architecturestudy.Injection

class ImagePresenter(val view : ImageContract.View) : ImageContract.Presenter{

    private val repository by lazy { Injection.provideNaverSearchRepository() }

    override fun taskSearch(keyword: String) {
        repository.getImage(
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> view.showErrorMessage(e.toString()) }
        )
    }
}
