package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.contract.BaseContract
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter(
    override val view : BaseContract.View
) : BaseContract.Presenter<BaseContract.View> {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun clearDisposable() = compositeDisposable.clear()
}