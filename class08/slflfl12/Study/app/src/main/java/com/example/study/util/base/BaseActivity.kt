package com.example.study.util.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(private val layoutResId: Int) : AppCompatActivity() {

    protected lateinit var binding: B

    abstract val vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        vm.clearDisposable()
        super.onDestroy()
    }

    fun showKeyboard() {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED,0
        )
    }

    fun hideKeyboard() {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            (currentFocus ?: View(this)).windowToken, 0
        )
    }
}