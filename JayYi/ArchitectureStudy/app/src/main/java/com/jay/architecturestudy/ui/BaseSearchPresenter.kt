package com.jay.architecturestudy.ui

import com.jay.architecturestudy.data.repository.NaverSearchRepository

abstract class BaseSearchPresenter(
    protected open val view: BaseSearchContract.View<*, *>,
    protected open val repository: NaverSearchRepository
) : BaseSearchContract.Presenter {

    override fun handleError(e: Throwable) {
        val message = e.message ?: return
        view.showErrorMessage(message)
    }
}