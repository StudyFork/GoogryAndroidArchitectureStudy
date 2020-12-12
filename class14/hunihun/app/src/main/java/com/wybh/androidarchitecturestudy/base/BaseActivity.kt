package com.wybh.androidarchitecturestudy.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.wybh.androidarchitecturestudy.BR

abstract class BaseActivity<B: ViewDataBinding, VM: BaseViewModel>(
    private val layoutId: Int
): AppCompatActivity() {

    abstract val vm: VM
    protected lateinit var binding: B
    protected val imm by lazy(LazyThreadSafetyMode.NONE) {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.run {
            setVariable(BR.vm, vm)
            lifecycleOwner = this@BaseActivity
        }
    }
}