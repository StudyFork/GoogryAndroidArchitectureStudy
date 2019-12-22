package com.jay.architecturestudy.ui

import com.jay.architecturestudy.data.repository.NaverSearchRepository

abstract class BasePresenter (
    protected open val view: BaseContract.View<*, *>,
    protected open val repository: NaverSearchRepository
): BaseContract.Presenter {

    override fun handlerError(e: Throwable) {
        val message = e.message ?: return
        view.showErrorMessage(message)
    }
}