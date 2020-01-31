package com.example.archstudy.base

import android.content.Context

interface BaseView {
    fun getApplicationContext() : Context
    fun showErrorMessage(error : Throwable)
    fun showMessage(msg : String)
}