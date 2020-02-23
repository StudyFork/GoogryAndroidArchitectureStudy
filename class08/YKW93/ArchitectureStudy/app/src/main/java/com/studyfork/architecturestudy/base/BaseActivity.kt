package com.studyfork.architecturestudy.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.studyfork.architecturestudy.BR

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: B

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.run {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = this@BaseActivity
        }

        viewModel.liveDataToastMessage.observe(this@BaseActivity, Observer<String> {
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        })
    }
}