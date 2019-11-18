package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.base.BasePresenter
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.source.NaverDataRepository
import com.egiwon.architecturestudy.data.source.NaverDataSource

class ContentsPresenter(
    private val contentsView: ContentsContract.View,
    private val naverDataRepository: NaverDataRepository
) : BasePresenter(), ContentsContract.Presenter {

    override fun loadContents(
        type: String,
        query: String
    ) {
        if (query.isBlank()) {
            contentsView.onFail(Throwable())
        } else {
            naverDataRepository.getContents(
                type = type,
                query = query,
                callback = object : NaverDataSource.Callback {
                    override fun onSuccess(resultList: List<Content.Item>) {
                        with(resultList) {
                            check(isNotEmpty())
                            contentsView.onUpdateUi(this)
                        }
                    }

                    override fun onFailure(throwable: Throwable) {
                        contentsView.onFail(throwable)
                    }
                }
            )
        }
    }

    override fun start() = Unit
}