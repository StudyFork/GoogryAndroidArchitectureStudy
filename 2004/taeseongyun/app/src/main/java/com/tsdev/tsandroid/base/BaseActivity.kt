package com.tsdev.tsandroid.base

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tsdev.tsandroid.eventbus.RxEventBusImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity<PRESENTER : BaseContract.Presenter, BINDING : ViewDataBinding> :
    AppCompatActivity() {

    protected val disposable = CompositeDisposable()

    abstract val presenter: PRESENTER

    abstract val binding: BINDING

    protected val rxJavaEvent by lazy {
        RxEventBusImpl()
    }

    inline fun movieSetDataBinding(
        @LayoutRes layoutInt: Int, activity: Activity,
        crossinline layoutBinding: (BINDING) -> BINDING
    ) =
        lazy { layoutBinding(DataBindingUtil.setContentView(activity, layoutInt)) }

    override fun onDestroy() {
        disposable.clear()
        presenter.clearCompositeDisposable()
        super.onDestroy()
    }
}