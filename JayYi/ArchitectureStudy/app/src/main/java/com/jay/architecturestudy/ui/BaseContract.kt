package com.jay.architecturestudy.ui

interface BaseContract {

    interface View {
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun subscribe()
        fun unsubscribe()

        fun handleError(e: Throwable)
    }

}