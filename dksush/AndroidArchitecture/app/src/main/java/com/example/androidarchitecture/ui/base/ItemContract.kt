package com.example.androidarchitecture.ui.base

interface ItemContract {

    interface View<T> {
        fun renderItems(items: List<T>)
        fun errorToast(msg: String?)
    }

    interface Presenter{
        fun requestList(text: String)

    }
}