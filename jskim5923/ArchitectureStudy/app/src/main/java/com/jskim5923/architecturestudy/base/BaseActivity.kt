package com.jskim5923.architecturestudy.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(
    @LayoutRes
    val layoutRes: Int
) : AppCompatActivity(), BaseContract.View {
    abstract val presenter: BaseContract.Presenter

    abstract override fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        initView()
    }

    override fun onDestroy() {
        presenter.clearCompositeDisposable()
        super.onDestroy()
    }
}