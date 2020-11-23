package com.jay.aas.base

import android.os.Bundle
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, P : BaseContract.Presenter>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity(layoutResId),
    BaseContract.View {

    protected lateinit var binding: B
    protected var progressBar: ProgressBar? = null

    protected abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    override fun onDestroy() {
        progressBar = null
        super.onDestroy()
    }

    override fun showProgressBar() {
        progressBar?.let { it.isVisible = true }
    }

    override fun hideProgressBar() {
        progressBar?.let { it.isGone = true }
    }
}