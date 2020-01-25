package com.example.architecturestudy.ui.kin

import com.example.architecturestudy.Injection
import com.example.architecturestudy.data.repository.NaverSearchRepository

class KinPresenter(
    val view : KinContract.View,
    private val repository: NaverSearchRepository?
    ) : KinContract.Presenter {

    override fun taskSearch(keyword: String) {
        repository?.getKin(
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> view.showErrorMessage(e.toString()) }
        )
    }
}