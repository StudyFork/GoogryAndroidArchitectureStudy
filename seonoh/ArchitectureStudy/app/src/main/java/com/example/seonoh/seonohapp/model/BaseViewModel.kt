package com.example.seonoh.seonohapp.model

import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel{
    protected val compositeDisposable = CompositeDisposable()
    protected val coinRepository = CoinRepositoryImpl()

    fun clearCompositeDisposable(){
        compositeDisposable.clear()
    }
}