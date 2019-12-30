package com.jay.architecturestudy.ui.kin

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo

class KinPresenter(
    override val view: KinContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), KinContract.Presenter {

    override fun search(keyword: String) {
        repository.getKin(
            keyword = keyword
        )
            .subscribe({ responseKin ->
                view.updateResult(responseKin.kins)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }
}