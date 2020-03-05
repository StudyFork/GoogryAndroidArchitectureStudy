package com.example.archstudy.base

interface BaseView {
    fun showErrorMessage(error : Throwable)
    fun showMessage(msg : String)
}