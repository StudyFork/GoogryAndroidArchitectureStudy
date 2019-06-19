package com.studyfirstproject

interface BaseView<T> {

    var presenter: T

    fun showProgress()

    fun hideProgress()
}