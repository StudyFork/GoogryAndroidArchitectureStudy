package app.ch.study.core

import io.reactivex.disposables.Disposable

interface BaseContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(error: String)
    }

    interface Presenter {
        fun addDisposable(disposable: Disposable): Boolean
        fun clearDisposable()
        fun handleError(e: Throwable): String
    }

}