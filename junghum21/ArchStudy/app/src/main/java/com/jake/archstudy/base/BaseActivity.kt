package com.jake.archstudy.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jake.archstudy.ext.toast

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    fun showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        toast(text, duration)
    }

    fun showToast(@StringRes stringResId: Int, duration: Int = Toast.LENGTH_SHORT) {
        toast(stringResId, duration)
    }

}