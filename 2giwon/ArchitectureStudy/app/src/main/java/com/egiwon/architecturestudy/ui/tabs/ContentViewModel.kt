package com.egiwon.architecturestudy.ui.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseViewModel
import com.egiwon.architecturestudy.ui.Tab
import com.egiwon.data.NaverDataRepository
import com.egiwon.data.model.ContentItem
import io.reactivex.android.schedulers.AndroidSchedulers

class ContentViewModel(
    private val tab: Tab,
    private val naverDataRepository: NaverDataRepository
) : BaseViewModel() {

    private val _searchQueryResultList = MutableLiveData<List<ContentItem>>()
    val searchQueryResultList: LiveData<List<ContentItem>> get() = _searchQueryResultList

    private val _isShowLoadingProgressBar = MutableLiveData<Boolean>()
    val isShowLoadingProgressBar: LiveData<Boolean> get() = _isShowLoadingProgressBar

    val searchQuery = MutableLiveData<String>()

    val isResultEmptyError: LiveData<Boolean> =
        Transformations.map(searchQueryResultList) { it.isNullOrEmpty() }

    fun loadContents() {
        if (searchQuery.value.isNullOrBlank()) {
            mutableErrorTextResId.value = (R.string.error_query_empty)
        } else {
            naverDataRepository.getContents(
                type = tab.name,
                query = searchQuery.value!!
            ).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _isShowLoadingProgressBar.value = true
                }
                .doAfterTerminate {
                    _isShowLoadingProgressBar.value = false
                }
                .subscribe({
                    _searchQueryResultList.value = it.contentItems
                }, {
                    mutableErrorTextResId.value = R.string.error_load_fail
                }).addDisposable()

        }
    }

    fun getCacheContents() {
        naverDataRepository.getCache(tab.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchQueryResultList.value = it.contentItems
                searchQuery.value = it.query
            }, {}).addDisposable()
    }

    fun loadContentsByHistory(query: String) {
        naverDataRepository.getContentsByHistory(tab.name, query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchQueryResultList.value = it.contentItems
                searchQuery.value = it.query
                loadContents()
            }, {
                mutableErrorTextResId.value = R.string.error_load_fail
            }).addDisposable()
    }

}