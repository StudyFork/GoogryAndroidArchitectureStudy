package com.hong.architecturestudy.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.hong.architecturestudy.BR

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutResId: Int) :
    AppCompatActivity() {
    protected lateinit var binding: VDB
    protected abstract val vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<VDB>(this, layoutResId).apply {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, vm)
        }
    }

    protected fun bind(action: VDB.() -> Unit) {
        binding.run(action)
    }

    protected infix fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(this@BaseActivity, Observer { observer(it) })
    }
}