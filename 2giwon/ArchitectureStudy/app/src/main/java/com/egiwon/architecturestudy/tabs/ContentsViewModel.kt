package com.egiwon.architecturestudy.tabs

import androidx.databinding.ObservableField
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseViewModel
import com.egiwon.architecturestudy.data.NaverDataRepository
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class ContentsViewModel(
    private val naverDataRepository: NaverDataRepository
) : BaseViewModel() {

    private val searchQueryResultList: BehaviorSubject<List<ContentItem>> = BehaviorSubject.create()

    private val searchQueryEmptyError: BehaviorSubject<Unit> = BehaviorSubject.create()

    private val showLoadingProgressBar: BehaviorSubject<Boolean> = BehaviorSubject.create()

    private val searchQueryResultEmptyList: BehaviorSubject<Unit> = BehaviorSubject.create()

    var query = ObservableField<String>()

    fun asSearchQueryListObservable(): Observable<List<ContentItem>> =
        searchQueryResultList.observeOn(AndroidSchedulers.mainThread())

    fun asSearchEmptyQueryErrorObservable(): Observable<Unit> =
        searchQueryEmptyError.observeOn(AndroidSchedulers.mainThread())

    fun asShowLoadingProgressBarObservable(): Observable<Boolean> =
        showLoadingProgressBar.observeOn(AndroidSchedulers.mainThread())

    fun asSearchQueryResultEmptyListObservable(): Observable<Unit> =
        searchQueryResultEmptyList.observeOn(AndroidSchedulers.mainThread())

    fun loadContents(type: Tab) {
        if (query.get().isNullOrBlank()) {
            searchQueryEmptyError.onNext(Unit)
        } else {
            naverDataRepository.getContents(
                type = type.name,
                query = query.get()!!
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showLoadingProgressBar.onNext(true)
                }
                .doAfterTerminate {
                    showLoadingProgressBar.onNext(false)
                }
                .subscribe({
                    if (it.contentItems.isNullOrEmpty()) {
                        searchQueryResultEmptyList.onNext(Unit)
                    } else {
                        searchQueryResultList.onNext(it.contentItems)
                    }
                }, {
                    searchQueryResultList.onError(it)
                }).addDisposable()

        }
    }

    fun getCacheContents(type: Tab) {
        naverDataRepository.getCache(type.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                searchQueryResultList.onNext(it.contentItems)
                this.query.set(it.query)
            }, {}).addDisposable()
    }

    fun loadContentsByHistory(type: Tab, query: String) {
        naverDataRepository.getContentsByHistory(type.name, query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                searchQueryResultList.onNext(it.contentItems)
                this.query.set(it.query)
                loadContents(type)
            }, {
                searchQueryResultList.onError(it)
            }).addDisposable()
    }

}