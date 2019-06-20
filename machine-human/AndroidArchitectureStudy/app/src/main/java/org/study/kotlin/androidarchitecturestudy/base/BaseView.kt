package org.study.kotlin.androidarchitecturestudy.base

/**
***************************
BaseView - structure

i = interface
f = function
v = variable
***************************

i = BaseView<T>

    v = presenter: T
    f = showProgress(text: String)
    f = hideProgress()

 */
//각기 다른 View들이 공통으로 갖는 것을 작성하는 인터페이스
interface BaseView<T> {
    var presenter: T
    fun showProgress(text: String)
    fun hideProgress()
}