package com.tsdev.tsandroid.base

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tsdev.tsandroid.eventbus.RxEventBusImpl
import com.tsdev.tsandroid.ui.viewmodel.BaseViewModel
import com.tsdev.tsandroid.ui.viewmodel.MainViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity<VM: BaseViewModel, BINDING : ViewDataBinding> :
    AppCompatActivity() {

    protected val disposable = CompositeDisposable()

    abstract val viewModel: VM

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
        super.onDestroy()
    }
}