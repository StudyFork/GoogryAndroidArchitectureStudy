package ado.sabgil.studyproject.view.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val disposables = CompositeDisposable()


    var showToastEventListeners: ((String) -> Unit)? = null

    var showProgressEventListeners: (() -> Unit)? = null

    var hideProgressEventListeners: (() -> Unit)? = null

    fun onDestroy() {
        disposables.clear()
    }

    protected fun showToast(message: String) {
        showToastEventListeners?.invoke(message)
    }

    protected fun showProgressBar() {
        showProgressEventListeners?.invoke()
    }

    protected fun hideProgressBar() {
        hideProgressEventListeners?.invoke()
    }

}