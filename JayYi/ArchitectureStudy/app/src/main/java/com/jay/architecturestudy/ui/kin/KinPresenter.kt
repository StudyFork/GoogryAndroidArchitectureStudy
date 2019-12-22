package com.jay.architecturestudy.ui.kin

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl

class KinPresenter (
    private val view: KinContract.View,
    private val repository: NaverSearchRepositoryImpl
): KinContract.Presenter {

    override fun search(keyword: String) {
        repository.getKin(
            keyword = keyword,
            success = { responseKin ->
                view.updateResult(responseKin.kins)
            },
            fail = { e ->
                val message = e.message ?: return@getKin
                view.showErrorMessage(message)
            }
        )
    }
}