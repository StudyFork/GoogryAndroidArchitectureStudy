package com.jskim5923.architecturestudy.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity(), BaseContract.View {
    protected lateinit var binding: B

    abstract val presenter: BaseContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        initView()
    }

    override fun onDestroy() {
        presenter.clearCompositeDisposable()
        super.onDestroy()
    }
}