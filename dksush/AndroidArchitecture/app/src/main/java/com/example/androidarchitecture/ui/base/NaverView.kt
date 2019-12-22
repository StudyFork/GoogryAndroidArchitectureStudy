package com.example.androidarchitecture.ui.base

interface NaverView<T> {

    fun renderItems(items: List<T>)
    fun errorToast(msg: String?)
}