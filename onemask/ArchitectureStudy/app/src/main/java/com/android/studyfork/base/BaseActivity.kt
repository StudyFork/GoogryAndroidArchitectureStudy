package com.android.studyfork.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BaseContract.Presenter>
    (@LayoutRes private val layoutRes: Int) : AppCompatActivity(), BaseContract.View<P> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        onStart()
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}