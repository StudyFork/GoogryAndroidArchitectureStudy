package com.egiwon.architecturestudy.tabs.bottomsheet

import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseViewModel
import com.egiwon.architecturestudy.data.NaverDataRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

class HistoryViewModel(
    private val naverDataRepository: NaverDataRepository
) : BaseViewModel() {

    private val searchHistoryResultList: BehaviorSubject<List<String>> = BehaviorSubject.create()

    fun asSearchHistoryResultListObservable(): Observable<List<String>> =
        searchHistoryResultList.observeOn(AndroidSchedulers.mainThread())

    fun getSearchQueryHistory(tab: Tab) =
        naverDataRepository.getContentQuerys(tab.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                searchHistoryResultList.onNext(it)
            }, {}).addDisposable()

}