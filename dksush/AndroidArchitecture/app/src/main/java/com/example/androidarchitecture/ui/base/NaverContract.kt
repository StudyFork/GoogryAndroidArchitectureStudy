package com.example.androidarchitecture.ui.base

interface NaverContract {

    interface View<T> : NaverView<T>
    interface Presenter<T> : NaverPresenter<T>
}