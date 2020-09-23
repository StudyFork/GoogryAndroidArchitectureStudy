package com.example.aas.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T : BaseContract.Presenter>(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId),
    BaseContract.View {

    abstract val presenter: T

    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        presenter.onDestroy()
        compositeDisposable.clear()
        super.onDestroy()
    }
}