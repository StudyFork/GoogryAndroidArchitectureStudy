package com.jay.architecturestudy.ui.kin

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BasePresenter

class KinPresenter(
    override val view: KinContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BasePresenter(view, repository), KinContract.Presenter {

    override fun search(keyword: String) {
        repository.getKin(
            keyword = keyword,
            success = { responseKin ->
                view.updateResult(responseKin.kins)
            },
            fail = { e ->
                handleError(e)
            }
        )
    }
}