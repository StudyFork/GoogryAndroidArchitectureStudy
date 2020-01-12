package com.egiwon.architecturestudy.ui.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.egiwon.architecturestudy.base.BaseViewModel
import com.egiwon.architecturestudy.data.NaverDataRepository
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem
import com.egiwon.architecturestudy.ui.Tab
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContentsViewModel(
    private val tab: Tab,
    private val naverDataRepository: NaverDataRepository
) : BaseViewModel() {

    private val _searchQueryResultList = MutableLiveData<List<ContentItem>>()
    val searchQueryResultList: LiveData<List<ContentItem>> get() = _searchQueryResultList

    private val _isShowLoadingProgressBar = MutableLiveData<Boolean>()
    val isShowLoadingProgressBar: LiveData<Boolean> get() = _isShowLoadingProgressBar

    private val _errorSearchQueryResult = MutableLiveData<Throwable>()
    val errorSearchQueryResult: LiveData<Throwable> get() = _errorSearchQueryResult

    private val _errorQueryEmpty = MutableLiveData<Throwable>()
    val errorQueryEmpty: LiveData<Throwable> get() = _errorQueryEmpty

    val isSearchResultListEmpty: LiveData<Boolean> =
        Transformations.map(_searchQueryResultList) { it.isNullOrEmpty() }

    val searchQuery = MutableLiveData<String>()

    fun loadContents() {
        if (searchQuery.value.isNullOrBlank()) {
            _errorQueryEmpty.value = Throwable()
        } else {
            naverDataRepository.getContents(
                type = tab.name,
                query = searchQuery.value!!
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _isShowLoadingProgressBar.value = true
                }
                .doAfterTerminate {
                    _isShowLoadingProgressBar.value = false
                }
                .subscribe({
                    _searchQueryResultList.value = it.contentItems
                }, {
                    _errorSearchQueryResult.value = it
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
                _errorSearchQueryResult.value = it
            }).addDisposable()
    }

}