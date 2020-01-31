package com.example.archstudy.base

interface BasePresenter<T> {

    fun bindView(view : T)
    fun unBindView()
}