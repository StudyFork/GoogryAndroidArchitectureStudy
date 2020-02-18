package com.example.study.util.base

import android.view.View
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(){

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}