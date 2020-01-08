package com.egiwon.architecturestudy.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseViewModel
import com.egiwon.architecturestudy.data.NaverDataRepository
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContentsViewModel(
    private val tab: Tab,
    private val naverDataRepository: NaverDataRepository
) : BaseViewModel() {

    private val _searchQueryResultList = MutableLiveData<List<ContentItem>>()

    private val _isShowLoadingProgressBar = MutableLiveData<Boolean>()

    private val _errorSearchQueryResult = MutableLiveData<Throwable>()

    private val _errorQueryEmpty = MutableLiveData<Throwable>()

    val isSearchResultListEmpty: LiveData<Boolean> =
        Transformations.map(_searchQueryResultList) {
            it.isNullOrEmpty()
        }

    val searchQuery = MutableLiveData<String>()

    val searchQueryResultList: LiveData<List<ContentItem>> get() = _searchQueryResultList

    val isShowLoadingProgressBar: LiveData<Boolean> get() = _isShowLoadingProgressBar

    val errorSearchQueryResult: LiveData<Throwable> get() = _errorSearchQueryResult

    val errorQueryEmpty: LiveData<Throwable> get() = _errorQueryEmpty

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