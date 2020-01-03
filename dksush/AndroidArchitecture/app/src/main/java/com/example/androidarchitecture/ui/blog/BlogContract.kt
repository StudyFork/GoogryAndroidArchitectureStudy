package com.example.androidarchitecture.ui.blog

interface BlogContract {
    interface View<T> {
        fun renderItems(items: List<T>)
        fun errorToast(msg: String?)
        fun blankInputText()
    }

    interface Presenter{
        suspend fun requestSearchHist()
        fun requestList(text: String)


    }
}