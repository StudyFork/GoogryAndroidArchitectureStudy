package com.egiwon.architecturestudy.ui.tabs.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.architecturestudy.base.BaseViewModel
import com.egiwon.architecturestudy.ui.Tab
import com.egiwon.data.NaverDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class HistoryViewModel(
    private val tab: Tab,
    private val naverDataRepository: NaverDataRepository
) : BaseViewModel() {

    private val _searchHistoryResult = MutableLiveData<List<String>>()

    val searchHistoryResult: LiveData<List<String>> get() = _searchHistoryResult

    fun getSearchQueryHistory() =
        naverDataRepository.getContentQueries(tab.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchHistoryResult.value = it
            }, {}).addDisposable()

}