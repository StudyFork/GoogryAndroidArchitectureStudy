package com.example.androidarchitecture.ui.base

interface ItemContract {

    interface View<T> {
        fun renderItems(items: List<T>)
        fun errorToast(msg: String?)
        fun blankInputText()
        fun inputKeyword(msg: String?)
        fun goneEmptyText()
    }

    interface Presenter{
        suspend fun requestSearchHist()
        fun requestList(text: String)

    }
}