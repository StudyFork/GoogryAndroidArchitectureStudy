package com.jay.architecturestudy.ui

import com.jay.architecturestudy.data.repository.NaverSearchRepository
import io.reactivex.disposables.CompositeDisposable

abstract class BaseSearchPresenter(
    protected open val view: BaseSearchContract.View<*, *>,
    protected open val repository: NaverSearchRepository
) : BaseSearchContract.Presenter {

    protected val disposables = CompositeDisposable()

    override fun subscribe() {
    }

    override fun unsubscribe() {
        disposables.clear()
    }
    
    override fun handleError(e: Throwable) {
        val message = e.message ?: return
        view.showErrorMessage(message)
    }
}