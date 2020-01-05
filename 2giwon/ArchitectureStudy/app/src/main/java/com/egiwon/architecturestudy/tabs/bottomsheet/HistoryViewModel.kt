package com.egiwon.architecturestudy.tabs.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseViewModel
import com.egiwon.architecturestudy.data.NaverDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class HistoryViewModel(
    private val tab: Tab,
    private val naverDataRepository: NaverDataRepository
) : BaseViewModel() {

    private val _searchHistoryResult = MutableLiveData<List<String>>()

    val searchHistoryResult: LiveData<List<String>> get() = _searchHistoryResult

    fun getSearchQueryHistory() =
        naverDataRepository.getContentQuerys(tab.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchHistoryResult.value = it
            }, {}).addDisposable()

}