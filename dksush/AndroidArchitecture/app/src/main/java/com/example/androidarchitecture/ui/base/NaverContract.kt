package com.example.androidarchitecture.ui.base

interface NaverContract {

    interface View<T> {
        fun renderItems(items: List<T>)
        fun errorToast(msg: String?)
    }

    interface Presenter : NaverPresenter
}