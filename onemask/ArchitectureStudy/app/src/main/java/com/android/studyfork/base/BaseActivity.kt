package com.android.studyfork.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>
(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    protected lateinit var binding: B
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    override fun onDestroy() {
        viewModel.clearDispoasble()
        super.onDestroy()
    }
}