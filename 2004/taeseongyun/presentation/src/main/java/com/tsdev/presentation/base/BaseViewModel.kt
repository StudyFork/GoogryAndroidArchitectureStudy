package com.tsdev.presentation.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    internal val compositeDisposable by lazy { CompositeDisposable() }
}