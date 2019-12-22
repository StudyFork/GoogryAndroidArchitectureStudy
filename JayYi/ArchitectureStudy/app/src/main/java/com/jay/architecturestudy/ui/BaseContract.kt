package com.jay.architecturestudy.ui

interface BaseContract {

    interface View<T, H> {
        var presenter: T

        fun updateResult(result: List<H>)

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun search(keyword: String)

        fun handlerError(e: Throwable)
    }

}