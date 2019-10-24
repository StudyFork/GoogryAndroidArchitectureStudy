package com.example.seonoh.seonohapp.model

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel{
    private val compositeDisposable = CompositeDisposable()

    fun clearCompositeDisposable(){
        compositeDisposable.clear()
    }

    fun add(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
}