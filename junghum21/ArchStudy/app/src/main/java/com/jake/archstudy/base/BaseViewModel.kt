package com.jake.archstudy.base

import androidx.databinding.ObservableField

abstract class BaseViewModel {

    val toast = ObservableField<String>()

    abstract fun start()

}