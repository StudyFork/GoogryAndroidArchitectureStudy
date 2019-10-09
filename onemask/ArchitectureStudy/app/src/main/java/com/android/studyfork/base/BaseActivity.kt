package com.android.studyfork.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, P : BaseContract.Presenter>
    (@LayoutRes private val layoutRes: Int) : AppCompatActivity(), BaseContract.View<P> {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        onStart()
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}