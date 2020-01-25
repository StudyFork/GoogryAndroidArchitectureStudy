package com.example.architecturestudy.ui.image

import com.example.architecturestudy.Injection
import com.example.architecturestudy.data.repository.NaverSearchRepository

class ImagePresenter(
    val view : ImageContract.View,
    private val repository: NaverSearchRepository?
    ) : ImageContract.Presenter{

    override fun taskSearch(keyword: String) {
        repository?.getImage(
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> view.showErrorMessage(e.toString()) }
        )
    }
}
