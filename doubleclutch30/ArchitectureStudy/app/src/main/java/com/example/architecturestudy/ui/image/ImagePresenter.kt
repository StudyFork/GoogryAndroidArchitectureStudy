package com.example.architecturestudy.ui.image

import com.example.architecturestudy.data.repository.NaverSearchRepository

class ImagePresenter(
    val view : ImageContract.View,
    private val repository: NaverSearchRepository?
    ) : ImageContract.Presenter{
    override fun taskSearch(isNetwork: Boolean, keyword: String) {
        repository?.getImage(
            isNetwork = isNetwork,
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> view.showErrorMessage(e.toString()) }
        )
    }

    override fun getLastData() {
        repository?.getLastImage(
            success = { view.showResult(it) },
            fail = { view.showEmpty("empty data") }
        )
    }
}
