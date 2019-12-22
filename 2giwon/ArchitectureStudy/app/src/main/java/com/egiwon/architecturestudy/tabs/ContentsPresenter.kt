package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BasePresenter
import com.egiwon.architecturestudy.data.NaverDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContentsPresenter(
    private val contentsView: ContentsContract.View,
    private val naverDataRepository: NaverDataRepository
) : BasePresenter(), ContentsContract.Presenter {

    override fun loadContents(
        type: Tab,
        query: String
    ) {
        if (query.isBlank()) {
            contentsView.showErrorQueryEmpty()
        } else {
            naverDataRepository.getContents(
                type = type.name,
                query = query
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    contentsView.showLoading()
                }
                .doAfterTerminate {
                    contentsView.hideLoading()
                }
                .subscribe({
                    if (it.contentItems.isNullOrEmpty()) {
                        contentsView.showErrorResultEmpty()
                    } else {
                        contentsView.showQueryResult(it.contentItems)
                    }
                }, {
                    contentsView.showErrorLoadFail()
                }).addDisposable()

        }
    }

    override fun getCacheContents(type: Tab) {
        naverDataRepository.getCache(type.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                contentsView.showCacheContents(it.contentItems, it.query)
            }, {}).addDisposable()
    }

    override fun loadContentsByHistory(type: Tab, query: String) {
        naverDataRepository.getContentsByHistory(type.name, query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                contentsView.showQueryHistoryResult(it.contentItems, it.query)
                loadContents(type, query)
            }, {
                contentsView.showErrorLoadFail()
            }).addDisposable()
    }

}