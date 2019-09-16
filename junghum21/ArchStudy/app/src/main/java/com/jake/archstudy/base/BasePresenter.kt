package com.jake.archstudy.base

interface BasePresenter<V> {

    val view: V

    fun onCreate()

}