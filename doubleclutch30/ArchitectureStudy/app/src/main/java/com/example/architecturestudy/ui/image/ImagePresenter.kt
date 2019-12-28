package com.example.architecturestudy.ui.image

import com.example.architecturestudy.Injection

class ImagePresenter(val view : ImageContract.View) : ImageContract.Presenter{

    private val repository by lazy { Injection.provideNaverSearchRepository() }
    override fun taskError(error: Throwable) {
        val msg = error.message.toString()
        view.showErrorMessage(msg)
    }

    override fun taskSearch(keyword: String) {
        repository.getImage(
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> taskError(e) }
        )
    }
}
