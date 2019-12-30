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
                val kins = responseKin.kins
                if (kins.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(kins)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }
}