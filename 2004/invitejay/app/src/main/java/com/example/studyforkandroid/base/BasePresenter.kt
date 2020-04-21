package com.example.studyforkandroid.base

interface BasePresenter<T> {
    fun setView(view: T)
}