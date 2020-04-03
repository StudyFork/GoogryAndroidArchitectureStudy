package com.byiryu.study.ui.mvvm.base.viewmodel

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(){
    val disposable = CompositeDisposable()

    fun onDestroy(){
        disposable.dispose()
    }
}