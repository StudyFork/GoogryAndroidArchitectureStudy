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

    protected fun handleErrorMessage(message: String) {
        showToastEventListeners?.invoke(message)
    }

    protected fun handleErrorMessage(throwable: Throwable) {
        throwable.printStackTrace()
        showToastEventListeners?.invoke("서버에서 데이터를 가져오는데에 실패하였습니다.")
    }

    protected fun startLoading() {
        showProgressEventListeners?.invoke()
    }

    protected fun endLoading() {
        hideProgressEventListeners?.invoke()
    }

}