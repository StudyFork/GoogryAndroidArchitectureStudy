package com.example.aas.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes private val contentLayoutId: Int) :
    AppCompatActivity() {

    protected val compositeDisposable = CompositeDisposable()
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, contentLayoutId)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}