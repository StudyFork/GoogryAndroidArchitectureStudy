package com.egiwon.architecturestudy.base

interface BaseView<T> : BaseContract.View<T> {
    val presenter: T
}