package com.egiwon.architecturestudy.tabs.bottomsheet

import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BasePresenter
import com.egiwon.architecturestudy.data.NaverDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class HistoryPresenter(
    private val historyView: HistoryContract.View,
    private val naverDataRepository: NaverDataRepository
) : BasePresenter(), HistoryContract.Presenter {

    override fun getSearchQueryHistory(tab: Tab) =
        naverDataRepository.getContentQuerys(tab.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                historyView.showSearchQueryHistory(it)
            }, {}).addDisposable()

}