package com.jay.architecturestudy.ui

import com.jay.architecturestudy.data.repository.NaverSearchRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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

    override fun updateSearchHistory(func: () -> Unit) {
        runOnIoScheduler(func)
    }

    override fun clearSearchHistory(func: () -> Unit) {
        runOnIoScheduler(func)
    }

    private fun runOnIoScheduler(action: () -> Unit) {
        Completable.fromCallable(action)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}