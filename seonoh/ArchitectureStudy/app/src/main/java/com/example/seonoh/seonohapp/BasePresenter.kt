package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.contract.BaseContract
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : BaseContract.Presenter {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun clearDisposable() = compositeDisposable.clear()
}