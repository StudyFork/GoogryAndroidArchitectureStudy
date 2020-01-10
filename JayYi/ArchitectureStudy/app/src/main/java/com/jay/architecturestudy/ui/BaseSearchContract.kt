package com.jay.architecturestudy.ui

interface BaseSearchContract {

    enum class ViewType(type: Int) {
        VIEW_SEARCH_BEFORE(0),
        VIEW_SEARCH_SUCCESS(1),
        VIEW_SEARCH_NO_RESULT(2),
        ;
    }

    interface View<T, H> : BaseContract.View {
        val presenter: T

        fun updateUi(keyword: String, data: List<H>)

        fun updateResult(result: List<H>)
    }

    interface Presenter : BaseContract.Presenter {
        fun search(keyword: String)
    }

}