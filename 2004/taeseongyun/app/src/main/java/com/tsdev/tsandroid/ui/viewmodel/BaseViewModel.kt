package com.tsdev.tsandroid.ui.viewmodel

import com.tsdev.tsandroid.ui.observe.ObserverProvider

abstract class BaseViewModel {
    abstract fun onBackKeyPressed()

    abstract fun onDestroy()

    abstract val observe: ObserverProvider
}