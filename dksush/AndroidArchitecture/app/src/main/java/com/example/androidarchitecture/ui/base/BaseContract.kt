package com.example.androidarchitecture.ui.base

interface BaseContract {

    interface View<T> {
        fun renderItems(items: List<T>)
        fun errorToast(msg: String?)
    }

    interface Presenter : BasePresenter
}