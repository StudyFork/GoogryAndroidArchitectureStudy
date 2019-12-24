package com.jay.architecturestudy.ui

interface BaseSearchContract {

    interface View<T, H>: BaseContract.View {
        val presenter: T

        fun updateResult(result: List<H>)
    }

    interface Presenter : BaseContract.Presenter {
        fun search(keyword: String)
    }

}