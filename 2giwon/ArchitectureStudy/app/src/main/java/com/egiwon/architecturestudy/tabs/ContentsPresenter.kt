package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.base.BasePresenter
import com.egiwon.architecturestudy.data.source.NaverDataRepository

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
                onSuccess = {
                    with(it) {
                        check(isNotEmpty())
                        contentsView.onUpdateUi(this)
                    }
                },
                onFailure = {
                    contentsView.onFail(it)
                }
            )
        }
    }

    override fun start() = Unit
}