package app.ch.study.core

interface BaseContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(error: String)
    }

    interface Presenter {
        fun clearDisposable()
        fun handleError(e: Throwable): String
    }

}