package com.studyfirstproject

interface BaseView<T> {

    val presenter: T

    fun showProgress()

    fun hideProgress()
}