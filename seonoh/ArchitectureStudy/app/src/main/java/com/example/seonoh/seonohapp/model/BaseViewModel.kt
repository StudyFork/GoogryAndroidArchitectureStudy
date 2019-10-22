package com.example.seonoh.seonohapp.model

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel{
    protected val compositeDisposable = CompositeDisposable()

    fun clearCompositeDisposable(){
        compositeDisposable.clear()
    }
}