package com.jay.architecturestudy.ui

interface BaseSearchContract {

    interface View<T, H> {
        val presenter: T

        fun updateResult(result: List<H>)

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun search(keyword: String)

        fun handleError(e: Throwable)
    }

}