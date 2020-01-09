package com.example.architecturestudy.ui.kin

import com.example.architecturestudy.Injection

class KinPresenter(val view : KinContract.View) : KinContract.Presenter {

    private val repository by lazy { Injection.provideNaverSearchRepository() }

    override fun taskSearch(keyword: String) {
        repository.getKin(
            keyword = keyword,
            success = { view.showResult(it) },
            fail = { e -> view.showErrorMessage(e.toString()) }
        )
    }
}