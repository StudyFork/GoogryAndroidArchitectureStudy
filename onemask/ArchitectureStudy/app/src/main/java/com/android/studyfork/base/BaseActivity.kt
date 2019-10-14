package com.android.studyfork.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewDataBinding>
(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var disposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        onStart()
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}